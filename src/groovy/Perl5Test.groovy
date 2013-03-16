import expect4j.matches.RegExpMatch
import org.apache.oro.text.regex.Pattern
import org.apache.oro.text.regex.Perl5Compiler
import org.apache.oro.text.regex.Perl5Matcher

/**
 * Created with IntelliJ IDEA.
 * User: davec
 * Date: 13-03-04
 * Time: 3:25 PM
 * To change this template use File | Settings | File Templates.
 */

class Perl5Test
{
  public static void main(String []args)
  {
    System.out.println("match");
    Perl5Matcher matcher = new Perl5Matcher();

    String buffer = "The quick brown fox jumps over the lazy dog ~\$";
    RegExpMatch instance = null;

    Perl5Compiler compiler = new Perl5Compiler();
    Pattern pattern = compiler.compile("~\\\$")
    def foo =  matcher.contains(buffer, pattern)
    printf " buffer ${foo?'':'does not '} contain ~\$"
  }
}
