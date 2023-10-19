# computational-workshop
Computational workshop tasks from 4th semester of Applied Mathematics and Informatics, Mathematics and Mechanics Faculty, St. Petersburg State University
## Tech stack
+ Java 17
+ No math libs
## Tasks
### 1. Non-linear equations
Let $F$ be continuous non-linear function of real argument $x\in\mathbb{R}$.

The task is to find every real solution of non-linear equation $F(x)=0$.

> [!NOTE] 
> The method will only find the odd ones because of its root separation strategy!

The reason for this is simple – the method will search for a segment $[a,b]$, in which $F(a)\cdot F(b)<0$. Since $F$ is continuous, it is obvious that $\exists x_0\in[a,b]:F(x_0)=0$ due to the [Intermediate value theorem (or Bolzano's theorem)](https://en.wikipedia.org/wiki/Intermediate_value_theorem).

However, the opposite cannot be said to be true: $F(x_0)=0\not\Rightarrow F(a)\cdot F(b)<0$. A simple counterexample is $f(x)=x^2$ with $x_0=0$. There is a way to detect such roots, but the method is far more complex.

Obviously, since this is a numerical method, every root will be approximate. However, you can pass a certain $\varepsilon$ value, where $\varepsilon$ parameter is an absolute error of each root.

Program implements several methods to approximate roots. 

#### Method a
