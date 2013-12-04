/*
 * Copyright 2010 NCHOVY
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.xtuple

import java.util.*


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class IptablesService  {
	private final Logger logger = LoggerFactory.getLogger(IptablesService.class.getName());

	private SshCommandRunner runner;
    private Server server;




	public IptablesService(Server server) {

        this.server = server;
        List <String> commands = new ArrayList<String>();
        //commands.add("iptables -L -n -t nat");
        commands.add("iptables-save | iptables-xml");
		this.runner = new SshCommandRunner(server, commands);
	}

	public void setCommandRunner(SshCommandRunner runner) {
		this.runner = runner;
	}


	public List<String> getChainNames() throws IOException {
		List<String> chains = new ArrayList<String>();


        runner.run();
        SshResult sshResult = runner.getSshResult();

        if (sshResult.isSuccess())
        {
            List<String> lines = sshResult.getLines();
            String xmlDoc = "";
            // first line has the command
            // last line has ~ssh
            for (int i=1; i < lines.size()-1;i++)
            {
                xmlDoc += lines[i]
            }
            def rules = new XmlParser().parseText(xmlDoc)
            def chainRules = rules.table.chain
            chainRules.each { it ->
              chains.add(it.'@name')
            }
        }
		return chains;
	}


	public List<Rule> getRules(String chainName) throws IOException {
		List<Rule> rules = new ArrayList<Rule>();
        List<String>lines = new ArrayList<String>();

		runner.run();
        SshResult sshResult = runner.getSshResult();
        if (sshResult.isSuccess()) lines = sshResult.getLines();

		boolean found = false;
        String xmlDoc = ""
		for (int i = 1; i < lines.size()-1; i++) {

			String line = lines.get(i);
            xmlDoc += lines[i]
        }
        def xmlRules = new XmlParser().parseText(xmlDoc)
        def chainRules = xmlRules.table.chain
        chainRules.each { chain ->
          chain.rule.each {rule ->
            try
            {
              FirewallRule newRule = new FirewallRule()
              newRule.condition = rule.conditions.text()
              if (newRule.condition == 'PREROUTING')
              {
                newRule.destination = rule.conditions.d.text()
                newRule.dport = rule.conditions.tcp.dport.text()
                newRule.toDestination = rule.actions.DNAT['to-destination'].text()
              }
              else if (newRule.condition == 'POSTROUTING')
              {
                newRule.iface = rule.conditions.o.text()
                newRule.source=rule.conditions.s.text()
              }
              else
              {
                newRule.iface = rule.conditions.i.text()
              }
              newRule.target = rule.actions[0].children()[0].name()
              newRule.protocol = rule.conditions.p.text()
              if ( newRule.protocol != '')
              {
                newRule.dport = rule.conditions[newRule.protocol].dport.text()
                newRule.sport = rule.conditions[newRule.protocol].sport.text()
              }

              rules.add(newRule)
            }
            catch(Exception ex)
            {
              println ex
            }

          }

        }

		return rules;
	}



	public void addRule(String chainName, int index, Rule rule) {
		String cmd = String.format("iptables -I %s %d %s", chainName, index, rule);
		logger.trace("kraken iptables: add rule [{}]", cmd);
        /*
		try {
			runner.run();
		} catch (IOException e) {
			logger.error("kraken iptables: failed to add rule.", e);
		}
		*/
	}


	public void addRule(String chainName, Rule rule) {
		String cmd = String.format("iptables -A %s %s", chainName, rule);
		logger.trace("kraken iptables: add rule [{}]", cmd);
        /*
		try {
			runner.run();
		} catch (IOException e) {
			logger.error("kraken iptables: failed to add rule.", e);
		}
		*/
	}

	public void removeRule(String chainName, int index) throws IOException {
		String cmd = String.format("iptables -D %s %d", chainName, index);
		runner.run();
	}

	public void addRule(Chain chain, int index, Rule rule) {
		addRule(chain.name(), index, rule);
	}

	public void addRule(Chain chain, Rule rule) {
		addRule(chain.name(), rule);
	}

	public List<Rule> getRules(Chain chain) throws IOException {
		return getRules(chain.name());
	}

	public void removeRule(Chain chain, int index) throws IOException {
		removeRule(chain.name(), index);
	}

	private static class CommandRunnerImpl implements CommandRunner {
		public List<String> run(String cmdline) throws IOException {
			List<String> lines = new ArrayList<String>();

			Process child = Runtime.getRuntime().exec(cmdline);
			try {
				child.waitFor();
			} catch (InterruptedException e) {
			}

			InputStream is = null;
			if (child.exitValue() == 0)
				is = child.getInputStream();
			else
				is = child.getErrorStream();

			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			while (true) {
				String line = br.readLine();
				if (line == null)
					break;

				lines.add(line);
			}

			if (child.exitValue() != 0)
				throw new IllegalStateException(lines.get(0));

			return lines;
		}
	}
}
