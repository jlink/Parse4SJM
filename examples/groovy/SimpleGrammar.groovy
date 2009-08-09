import sjm.grammar.Grammar

def mathGrammar = new Grammar("a simple math grammar")

mathGrammar.defineRule("term = Num") { matches, stack ->
    stack.push(matches[0].value())
}

def result = mathGrammar.parse("5.1")
assert result.stack.pop() == 5.1