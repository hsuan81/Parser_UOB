import computation.contextfreegrammar.*;
import java.util.ArrayList;
import java.util.*;

public class MyGrammar {
	public static ContextFreeGrammar makeGrammar(){
		// You can write your code here to make the context-free grammar from the assignment
    Variable s = new Variable('S');
    Variable s0 = new Variable("S0");
    Variable t = new Variable('T');
    Variable f = new Variable('F');
    Variable f1 = new Variable("F1");
    Variable t1 = new Variable("T1");
    Variable s1 = new Variable("S1");
    Variable a = new Variable('A');
    Variable b = new Variable('B');
    Variable c = new Variable('C');
    Variable d = new Variable('D');
    Variable e = new Variable('E');
    Variable g = new Variable('G');
    Variable h = new Variable('H');

    Terminal lbrace = new Terminal('(');
    Terminal rbrace = new Terminal(')');
    Terminal one = new Terminal('1');
    Terminal zero = new Terminal('0');
    Terminal x = new Terminal('x');
    Terminal times = new Terminal('*');
    Terminal add = new Terminal('+');

    Rule r11 = new Rule(s0, new Word(s, s1));
    Rule r12 = new Rule(s0, new Word(t, t1));
    Rule r13 = new Rule(s0, new Word(a, f1));
    Rule r14 = new Rule(s0, new Word(one));
    Rule r15 = new Rule(s0, new Word(zero));
    Rule r16 = new Rule(s0, new Word(x));

    Rule r21 = new Rule(s, new Word(s, s1));
    Rule r22 = new Rule(s, new Word(t, t1));
    Rule r23 = new Rule(s, new Word(a, f1));
    Rule r24 = new Rule(s, new Word(one));
    Rule r25 = new Rule(s, new Word(zero));
    Rule r26 = new Rule(s, new Word(x));

    Rule r31 = new Rule(t, new Word(t, t1));
    Rule r32 = new Rule(t, new Word(a, f1));
    Rule r33 = new Rule(t, new Word(one));
    Rule r34 = new Rule(t, new Word(zero));
    Rule r35 = new Rule(t, new Word(x));

    Rule r41 = new Rule(f, new Word(a, f1));
    Rule r42 = new Rule(f, new Word(one));
    Rule r43 = new Rule(f, new Word(zero));
    Rule r44 = new Rule(f, new Word(x));

    Rule r5 = new Rule(f1, new Word(s, e));
    Rule r6 = new Rule(t1, new Word(g, f));
    Rule r7 = new Rule(s1, new Word(h, t));
    Rule r81 = new Rule(a, new Word(lbrace));
    Rule r82 = new Rule(e, new Word(rbrace));
    Rule r83 = new Rule(b, new Word(one));
    Rule r84 = new Rule(c, new Word(zero));
    Rule r85 = new Rule(d, new Word(x));
    Rule r86 = new Rule(g, new Word(times));
    Rule r87 = new Rule(h, new Word(add));

    List<Rule> rules = new ArrayList<Rule>();
    rules.addAll(Arrays.asList(r11, r12, r13, r14, r15, r16));
    rules.addAll(Arrays.asList(r21, r22, r23, r24, r25, r26));
    rules.addAll(Arrays.asList(r31, r32, r33, r34, r35));
    rules.addAll(Arrays.asList(r41, r42, r43, r44));
    rules.addAll(Arrays.asList(r5, r6, r7, r81, r82, r83, r84, r85, r86, r87));
    
    ContextFreeGrammar cfg = new ContextFreeGrammar(rules);
		return cfg;
	}
}
