package com.gpzuestc.util;
/**
 * Description:  
 * @author: guopengzhang@sohu-inc.com 
 * @date: Jun 18, 2012
 * 
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * deal html escape characters
 * @author Administrator
 * @since 2009-12-27
 */
public class HTMLEscapeCharacter {
	private static final String[] CODES = new String[]{
		"&fnof;","&Alpha;","&Beta;","&Gamma;","&Delta;","&Epsilon;","&Zeta;","&Eta;","&Theta;","&Iota;","&Kappa;",
		"&Lambda;","&Mu;","&Nu;","&Xi;","&Omicron;","&Pi;","&Rho;","&Sigma;","&Tau;","&Upsilon;","&Phi;","&Chi;",
		"&Psi;","&Omega;","&alpha;","&beta;","&gamma;","&delta;","&epsilon;","&zeta;","&eta;","&theta;","&iota;",
		"&kappa;","&lambda;","&mu;","&nu;","&xi;","&omicron;","&pi;","&rho;","&sigmaf;","&sigma;","&tau;","&upsilon;",
		"&phi;","&chi;","&psi;","&omega;","&thetasym;","&upsih;","&piv;","&bull;","&hellip;","&prime;","&Prime;",
		"&oline;","&frasl;","&weierp;","&image;","&real;","&trade;","&alefsym;","&larr;","&uarr;","&rarr;",
		"&darr;","&harr;","&crarr;","&lArr;","&uArr;","&rArr;","&dArr;","&hArr;","&forall;","&part;","&exist;",
		"&empty;","&nabla;","&isin;","&notin;","&ni;","&prod;","&sum;","&minus;","&lowast;","&radic;","&prop;",
		"&infin;","&ang;","&and;","&or;","&cap;","&cup;","&int;","&there4;","&sim;","&cong;","&asymp;","&ne;",
		"&equiv;","&le;","&ge;","&sub;","&sup;","&nsub;","&sube;","&supe;","&oplus;","&otimes;","&perp;","&sdot;",
		"&lceil;","&rceil;","&lfloor;","&rfloor;","&lang;","&rang;","&loz;","&spades;","&clubs;","&hearts;","&diams;",
		"&nbsp;","&iexcl;","&cent;","&pound;","&curren;","&yen;","&brvbar;","&sect;","&uml;","&copy;","&ordf;","&laquo;",
		"&not;","&shy;","&reg;","&macr;","&deg;","&plusmn;","&sup2;","&sup3;","&acute;","&micro;","&para;","&middot;",
		"&cedil;","&sup1;","&ordm;","&raquo;","&frac14;","&frac12;","&frac34;","&iquest;","&Agrave;","&Aacute;","&Acirc;",
		"&Atilde;","&Auml;","&Aring;","&AElig;","&Ccedil;","&Egrave;","&Eacute;","&Ecirc;","&Euml;","&Igrave;","&Iacute;",
		"&Icirc;","&Iuml;","&ETH;","&Ntilde;","&Ograve;","&Oacute;","&Ocirc;","&Otilde;","&Ouml;","&times;","&Oslash;",
		"&Ugrave;","&Uacute;","&Ucirc;","&Uuml;","&Yacute;","&THORN;","&szlig;","&agrave;","&aacute;","&acirc;","&atilde;",
		"&auml;","&aring;","&aelig;","&ccedil;","&egrave;","&eacute;","&ecirc;","&euml;","&igrave;","&iacute;","&icirc;",
		"&iuml;","&eth;","&ntilde;","&ograve;","&oacute;","&ocirc;","&otilde;","&ouml;","&divide;","&oslash;","&ugrave;",
		"&uacute;","&ucirc;","&uuml;","&yacute;","&thorn;","&yuml;","&quot;","&amp;","&lt;","&gt;","&OElig;","&oelig;",
		"&Scaron;","&scaron;","&Yuml;","&circ;","&tilde;","&ensp;","&emsp;","&thinsp;","&zwnj;","&zwj;","&lrm;","&rlm;",
		"&ndash;","&mdash;","&lsquo;","&rsquo;","&sbquo;","&ldquo;","&rdquo;","&bdquo;","&dagger;","&Dagger;","&permil;",
		"&lsaquo;","&rsaquo;","&euro;"};
	/**
	 * Analyse if one string like &****; is a escape character.
	 * @param code
	 * @return
	 */
	public static boolean isContain(String code){
		boolean f = false;
		for(String s:CODES){
			if(s.equals(code)){
				f=true;
				break;
			}
		}
		return f;
	}
	/**
	 * find characters from file
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception{
		File f = new File("\\escape_character.TXT");
		BufferedReader br =new BufferedReader(new InputStreamReader(new FileInputStream(f)));
		String str=null; 
		StringBuffer sb = new StringBuffer();
		while((str=br.readLine())!=null) 
		{
			sb.append(str);
		}
		String code = sb.toString();
		System.out.println(code);
		char[] chars = code.toCharArray();
		String temp="";
		String re = "";
		boolean fl = false;
		for(char c:chars){
			if(c=='&') fl=true;
			if(c==';') fl=false;
			if(fl){
				temp+=String.valueOf(c);
			}else if(!"".equals(temp))
				temp+=";";
			if(temp.startsWith("&")&&temp.endsWith(";")&&!temp.startsWith("&#")){
				re+="\""+temp+"\",";
				temp="";
			}else if(temp.startsWith("&#")&&temp.endsWith(";"))temp="";
		}
		System.out.println(re);
		System.out.println(re.split(",").length);
	}
}
