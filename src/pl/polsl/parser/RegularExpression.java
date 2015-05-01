package pl.polsl.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.polsl.servlet.WMachineServletBase;

/**
 * Class to build complex regular expressions.
 * @author Tomasz Rzepka
 * @version 1.0
 */
public class RegularExpression {
	/** StringBuilder used to build regular expression. */
	StringBuilder builder = new StringBuilder();
	
	/** Compiled pattern. */
	Pattern pattern = null;
	
	/**
	 * Method to get string whith regular expression.
	 * @return Created regular expression.
	 */
	@Override
	public String toString() {
		return builder.toString();
	}
	
	/**
	 * Match text exactly as it is.
	 * @param text text to be matched
	 * @return This RegularExpression instance.
	 */
	public RegularExpression exactly(String text) {
		builder.append(text);
		return this;
	}
	
	/**
	 * Match another regular expression.
	 * @param expression regular expression to be matched
	 * @return This RegularExpression instance.
	 */
	public RegularExpression expression(RegularExpression expression) {
		builder.append(expression.toString());
		return this;
	}
	
	/**
	 * Match many elements.
	 * @param min minimum matches count
	 * @param max maximum matches count. Integer.MAX_VALUE means infinity.
	 * @return This RegularExpression instance.
	 */
	public RegularExpression many(Integer min, Integer max) {
		if(min == 0 && max == Integer.MAX_VALUE)
			builder.append("*");
		else if(min == 1 && max == Integer.MAX_VALUE)
			builder.append("+");
		else {
			builder.append("{");
			builder.append(min.toString());
			builder.append(",");
			builder.append(max.toString());
			builder.append("}");
		}
		return this;
	}
	
	/**
	 * Match at least one element.
	 * @return This RegularExpression instance.
	 */
	public RegularExpression many() {
		return many(1, Integer.MAX_VALUE);
	}
	
	/**
	 * Match no element or any amount.
	 * @return This RegularExpression instance.
	 */
	public RegularExpression any() {
		return many(0, Integer.MAX_VALUE);
	}
	
	/**
	 * Put entire expression in a group.
	 * @return This RegularExpression instance.
	 */
	public RegularExpression group() {
		builder.insert(0, "(");
		builder.append(")");
		return this;
	}
	
	/**
	 * Match character class.
	 * @param expression characters to be matched
	 * @return This RegularExpression instance.
	 */
	public RegularExpression characters(String expression) {
		builder.append("[");
		builder.append(expression);
		builder.append("]");
		return this;
	}
	
	/**
	 * Match any whitespace.
	 * @return This RegularExpression instance.
	 */
	public RegularExpression whitespace() {
		builder.append("\\s");
		return this;
	}
	
	/**
	 * Match any digit.
	 * @return This RegularExpression instance.
	 */
	public RegularExpression digit() {
		return this.characters("0-9");
	}
	
	/**
	 * Match any letter.
	 * @return This RegularExpression instance.
	 */
	public RegularExpression letter() {
		return this.characters("a-zA-Z");
	}
	
	/**
	 * Match any upper case letter.
	 * @return This RegularExpression instance.
	 */
	public RegularExpression upperCaseLetter() {
		return this.characters("A-Z");
	}
	
	/**
	 * Match any lower case letter.
	 * @return This RegularExpression instance.
	 */
	public RegularExpression lowerCaseLetter() {
		return this.characters("a-z");
	}
	
	/**
	 * Match any alphanumeric character (digit or upper/lower case letter).
	 * @return This RegularExpression instance.
	 */
	public RegularExpression alphanumeric() {
		return this.characters("a-zA-Z0-9");
	}

	/**
	 * Start alternative version of match.
	 * @return This RegularExpression instance.
	 */
	public RegularExpression or() {
		builder.append("|");
		return this;
	}
	
	/**
	 * Make element optional.
	 * @return This RegularExpression instance.
	 */
	public RegularExpression optional() {
		builder.append("?");
		return this;
	}
	
	/**
	 * Ensure that element is followed by given expression.
	 * Uses positive lookahead.
	 * @param expression expression that must follow previous element
	 * @return This RegularExpression instance.
	 */
	public RegularExpression followedBy(Object expression) {
		builder.append("(?=");
		builder.append(expression.toString());
		builder.append(")");
		return this;
	}
	
	/**
	 * Ensure that element is not followed by given expression.
	 * Uses negative lookahead.
	 * @param expression expression that must not follow previous element
	 * @return This RegularExpression instance.
	 */
	public RegularExpression notFollowedBy(Object expression) {
		builder.append("(?!");
		builder.append(expression.toString());
		builder.append(")");
		return this;
	}
	
	/**
	 * Ensure that element is preceded by given expression.
	 * Uses positive lookbehind.
	 * @param expression expression that must preced next element
	 * @return This RegularExpression instance.
	 */
	public RegularExpression precededBy(Object expression) {
		builder.append("(?<=");
		builder.append(expression.toString());
		builder.append(")");
		return this;
	}
	
	/**
	 * Ensure that element is not preceded by given expression.
	 * Uses negative lookbehind.
	 * @param expression expression that must not preced next element
	 * @return This RegularExpression instance.
	 */
	public RegularExpression notPrecededBy(Object expression) {
		builder.append("(?<!");
		builder.append(expression.toString());
		builder.append(")");
		return this;
	}
	
	/**
	 * Open new group.
	 * @return This RegularExpression instance.
	 */
	public RegularExpression openGroup() {
		builder.append("(");
		return this;
	}
	
	/**
	 * Close open group.
	 * @return This RegularExpression instance.
	 */
	public RegularExpression closeGroup() {
		builder.append(")");
		return this;
	}
	
	/**
	 * Match anything (match dot).
	 * @return This RegularExpression instance.
	 */
	public RegularExpression anything() {
		builder.append(".");
		return this;
	}
	
	/**
	 * Match beginning of string.
	 * @return This RegularExpression instance.
	 */
	public RegularExpression begin() {
		builder.append("^");
		return this;
	}
	
	/**
	 * Match end of string.
	 * @return This RegularExpression instance.
	 */
	public RegularExpression end() {
		builder.append("$");
		return this;
	}
	
	/**
	 * Compile created regular expression.
	 * @return Pattern containing compiled regular expression.
	 */
	public Pattern compile() {
		pattern = Pattern.compile(builder.toString()); 
		return pattern;
	}
	
	/**
	 * Compile created regular expression with flags.
	 * @param flags flags used to compile regular expression.
	 * @return Pattern containing compiled regular expression.
	 * @see Pattern#compile(String regex, int flags)
	 */
	public Pattern compile(int flags) {
		pattern = Pattern.compile(builder.toString(), flags); 
		return pattern;
	}
	
	/**
	 * Match groups defined in this regular expression.
	 * Regular expression must be compiled before groups can be matched.
	 * If it is not compiled when function is called, compilation will
	 * be performed first.
	 * @param input input string to be matched
	 * @return Matched groups or null if input does not match.
	 */
	public String[] matchGroups(String input) {
		if(pattern == null)
			pattern = compile();
		Matcher m = pattern.matcher(input);
		if(!m.matches())
			return null;
		String[] groups = new String[m.groupCount()];
		for(int i = 0; i < groups.length; ++i)
			groups[i] = m.group(i + 1);
		return groups;
	}
}
