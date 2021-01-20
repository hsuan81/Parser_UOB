import java.util.*;
import computation.contextfreegrammar.*;
import computation.parser.*;
import computation.parsetree.*;
import computation.derivation.*;

public class Parser implements IParser {
  public boolean isInLanguage(ContextFreeGrammar cfg, Word w){
    int wordLen = w.length();
    ArrayList<Derivation> derivations = generateDerivations(cfg, wordLen);

    for(Derivation d: derivations){
      if(w.equals(d.getLatestWord())){
        System.out.println(d.getLatestWord().toString());
        return true;
      }
    }  
    return false;
  }
  
  public ArrayList<Derivation> generateDerivations(ContextFreeGrammar cfg, int wordLen){
    Word startVariable = new Word(cfg.getStartVariable());
    Derivation activeDeriv = new Derivation(startVariable);
    ArrayList<Derivation> activeList = new ArrayList<>();
    activeList.add(activeDeriv);
    int step = 0;
    List<Rule> rules = cfg.getRules();

    while(step < 2 * wordLen - 1){
      ArrayList<Derivation> newList = new ArrayList<>();
      for(Derivation d: activeList){
        Word latestWord = d.getLatestWord();

        for(int i=0; i<latestWord.length(); i++){ 
          Word posWord = new Word(latestWord.get(i));
          if(posWord.isTerminal()){
            continue;
          }
          for(Rule r: rules){
            Word v = new Word(r.getVariable());
            if(posWord.equals(v)) {
              Derivation newActiveDeriv = new Derivation(d);
              Word replace = r.getExpansion();
              Word newWord = latestWord.replace(i, replace);
              newActiveDeriv.addStep(newWord, r, step);
              newList.add(newActiveDeriv); 
            }
          }
        }
      }
      activeList = (ArrayList<Derivation>)newList.clone(); 
      step++;
    }
    return activeList;
  }


  public ParseTreeNode generateParseTree(ContextFreeGrammar cfg, Word w) {
    // ensure w is in cfg
    if(!isInLanguage(cfg, w)){
      return null;
    }
    ArrayList<Derivation> derivations = generateDerivations(cfg, w.length());
    // establish PTN of all terminals
    ArrayList<ParseTreeNode> nodes = new ArrayList<>();
    Set<Terminal> terminals = cfg.getTerminals();

    // search for the targeted derivation
    Derivation find = null;
    for(Derivation d: derivations){
      if(w.equals(d.getLatestWord())){
        find = new Derivation(d);
      }
    }
    //  extract rules of each step
    Iterator it = find.iterator();
    while(it.hasNext()){
      Step s = (Step)it.next();

      // Break the while loop when it goes through all valid steps
      if(s.getIndex() == -1){
        break;
      } 
      ArrayList<ParseTreeNode> temp = new ArrayList<>();
      ArrayList<ParseTreeNode> remove = new ArrayList<>();
      Rule r = s.getRule();
      Variable v = r.getVariable();
      Word e = r.getExpansion();
      if(e.equals(Word.emptyWord)){
        nodes.add(ParseTreeNode.emptyParseTree(v));
        continue;
      }
      int eLen = e.length();
      int index = 0;
      // decide expansion node and its children
      while(eLen > 0){
        Symbol c = e.get(index);
        if(terminals.contains(c)){
          temp.add(new ParseTreeNode(c));
          eLen -= 1;
        }
        else{
          for(ParseTreeNode n: nodes){
            if(e.get(index) == n.getSymbol()){
              temp.add(n);
              remove.add(n);
              eLen -= 1;
              index += 1;
              // all expansion found, avoid index out of bound
              if(eLen == 0){
                break;
              }  
            }
          }
        }
        
      }
      
      // form the parsetreenode and update resulted parsetree
      if(temp.size() > 1){
        ParseTreeNode np = new ParseTreeNode(v, temp.get(0), temp.get(1));
        nodes.add(np);
        for(ParseTreeNode i: remove){
          nodes.remove(i);
        }
        
      }
      else{
        ParseTreeNode np = new ParseTreeNode(v, temp.get(0));
        nodes.add(np);
      }
    }

    int nLen = nodes.size() - 1;
    ParseTreeNode result = nodes.get(nLen);
    return result;  
  }
}