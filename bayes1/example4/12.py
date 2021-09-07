class BayesianNetwork:
    def __init__(self, name, states, graph):
        self.name = name
        self.graph = graph
        self.states = states

    #TODO improve parameters event1, event2 and row[3] to be more dinamicaly
    def __calculate_isolated_nodes(self, event1, event2, isolated_nodes):
        result = 0
        table_matrix = []
        # Get tables to calculate
        for node in isolated_nodes:
            table_matrix.append(self.states[node].value)

        for table in table_matrix:
            for row in table:
                if (row[0] == event1 and row[1] == event2):
                    result = row[3]
                
        return result

    def __calculate_edges(self, edges):
        result = []
        for edge in edges:
            #TODO Improve the way to get the value of the edges for work with table edges
            result.append(self.states[edge].value[0])
        return result

    def probability(self, events):
        edges_values = self.__calculate_edges(self.generate_edges())
        isolateds_values = self.__calculate_isolated_nodes(events[0], events[1], self.find_isolated_nodes())
        return isolateds_values * edges_values[1] *(1 - edges_values[0])

    def generate_edges(self):
        """ returns a dictionary of node and neighbour """
        edges = {}
        for node in self.graph:
            for neighbour in self.graph[node]:
                edges[node] = neighbour

        return edges

    def find_isolated_nodes(self):
        """ returns a set of isolated nodes. """
        isolated = set()
        for node in self.graph:
            if not self.graph[node]:
                isolated.add(node)
        return isolated


class Node:
    def __init__(self, name, value):
        self.name = name
        self.value = value


print("""
Uma determinada floresta sofre com problemas de incêndio ocasionalmente.
Sabe-se que os incêndios são causados por acampamentos no perímetro ou tempestades de raios.
""")

print("""
A - Desenhe a rede bayesiana do problema apresentado
""")

print("""
A rede bayesiana é um gráfo direcionado acíclico, do inglês Directed Acyclic Graph (DAG)
então dada as definições, podemos representar o seguinte grafo:
""")

graph = {"tempestade": {"fogo"}, "acampamento": {"fogo"}, "fogo": {}}
bsn = BayesianNetwork("Fogo na floresta rede bayesiana", [], graph)
print("====> Grafo da rede bayesiana de fogo na floresta", bsn.generate_edges(), bsn.find_isolated_nodes())

print("""
B - Dada a tabela de probabilidade condicional, calcule a probabilidade de não haver fogo na floresta sabendo que há um acampamento mas não houve tempestade de raios.

As colunas são referente a ACAMPAMENTO, TEMPESTADE, FOGO, NÃO FOGO
""")

# Tabela de probabilidade condicional, do inglês Conditional Probability Table (CPT) 
# como esse evento deriva de acampamento ou tempestade precisamos
# calcular a tabela verdade de ele acontecer

table = [
    # Cada linha representa 100%, por exemplo se 0.8 é a probabilidade fogo então 0.2 é de
    # não fogo
    [True, True, 0.8, 0.2],
    [True, False, 0.2, 0.8],
    [False, True, 0.3, 0.7],
    [False, False, 0.2, 0.8]

]

acampamento_probabilidade = [ 0.1 ]
tempestade_probabilidade = [ 0.2 ]
print("Tabela condicional", table)
print("Probabilidade de acampamento", acampamento_probabilidade)
print("Probabilidade de tempestade", tempestade_probabilidade)

acampamento = Node("acampamento", acampamento_probabilidade)
tempestade = Node("tempestade", tempestade_probabilidade)
fogo = Node("fogo", table)

states = {"acampamento": acampamento, "fogo": fogo, "tempestade": tempestade}
bsn = BayesianNetwork("Fogo na floresta rede bayesiana", states, graph)


print("===> Probabilidade de tempestade", bsn.probability([True, False]))

