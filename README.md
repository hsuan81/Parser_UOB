# Parser
A coursework of UOB CS program to develop a parser in Java.

## Purpose
Use the third party libraries to develop a parser processing context-free language in Chomsky Normal Form in Java.

## Requirements

 1. `MyGrammar.java` is to build the Chomsky Normal Form of context-free language through third party libraries provided in the folder.
 2. `Parser.jave` has two functions: 
	 * `boolean isInLanguage(ContextFreeGrammar cfg, Word w)` : The algorithm should list all derivations in the grammar with 2n − 1 steps, where n is the length of w, unless n = 0, in which case list all derivations with one step. If the input word is in the derivations, return true. Otherwise, return false. 
	 * `ParseTreeNode generateParseTree(ContextFreeGrammar cfg,Word w)`: Return the parse tree of the input word based on the context-free grammar. In building the parse tree, the tree should be drawn from the bottom of the tree upwards. First of all create a number of ParseTreeNode objects – one for each of the final terminals in the parse tree. You will also need to find a way to keep track of these objects. Then, by following the steps of the full derivation back- wards, you can work out which ParseTreeNode objects need to be combined into new ParseTreeNodes. Eventually you’ll reach the start variable of the grammar, and this will be your final ParseTreeNode object.
 3. Auxiliary function is welcome to add. 
 4. Third party libraries are in folders `computation` and `thirdparty`. 

> Written with [StackEdit](https://stackedit.io/).
