import sjm.grammar.Grammar

def mathGrammar = new Grammar("a simple math grammar")
mathGrammar.discardAllConstants()

mathGrammar.defineRule("expression = term ('+' term)*;") { matches, stack ->
    def sum = matches.inject(0) {sum, each -> sum + each}
    stack.push(sum)
}
mathGrammar.defineRule("term = Num;") { matches, stack ->
    stack.push(matches[0].value())
}

def result = mathGrammar.parse("5.1")
assert result.stack.pop() == 5.1

result = mathGrammar.parse("5.1 + 2.0 + 1.8")
assert result.stack.pop() == 8.9