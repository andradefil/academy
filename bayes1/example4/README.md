# BayesianNetwork

## Modelo treinado para calcular a probabilidade de um incêndio em uma floresta sabendo que há um acampamento mas não houve tempestade de raios

## Executar usando python
```
$ python3 ./12.py
```

Uma determinada floresta sofre com problemas de incêndio ocasionalmente.
Sabe-se que os incêndios são causados por acampamentos no perímetro ou tempestades de raios.


A - Desenhe a rede bayesiana do problema apresentado


A rede bayesiana é um gráfo direcionado acíclico, do inglês Directed Acyclic Graph (DAG)
então dada as definições, podemos representar o seguinte grafo:

*====> Grafo da rede bayesiana de fogo na floresta {'tempestade': 'fogo', 'acampamento': 'fogo'} {'fogo'}*

B - Dada a tabela de probabilidade condicional, calcule a probabilidade de não haver fogo na floresta sabendo que há um acampamento mas não houve tempestade de raios.

As colunas são referente a ACAMPAMENTO, TEMPESTADE, FOGO, NÃO FOGO

Tabela condicional [[True, True, 0.8, 0.2], [True, False, 0.2, 0.8], [False, True, 0.3, 0.7], [False, False, 0.2, 0.8]]
Probabilidade de acampamento [0.1]
Probabilidade de tempestade [0.2]

*===> Probabilidade de tempestade 0.06400000000000002*

Referencias:

https://www.youtube.com/watch?v=3dVEliviEwY&ab_channel=FernandoJ.VonZuben
https://www.python-course.eu/graphs_python.php
https://www.w3schools.com/python/python_classes.asp
https://pomegranate.readthedocs.io/en/latest/BayesianNetwork.html

