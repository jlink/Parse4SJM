import sjm.grammar.Grammar

def mathGrammar = new Grammar("a simple math grammar")

mathGrammar.rule("start := Integer") { matches, stack ->
    stack.push(matches[0].value())
}

def result = mathGrammar.parse "5"

println result.stack.pop()

