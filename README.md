
SKNoise

###### The ultimate noise addon (No, not sound)

[![SkriptHubViewTheDocs](http://skripthub.net/static/addon/ViewTheDocsButton.png)](http://skripthub.net/docs/?addon=SKNoise)

---

Welcome to the official repository for SKNoise, a Skript addon that includes noise expressions for perlin noise and simplex noise!

I just recently started Java development, so yes this code is kind of messy but it will be receiving updates actively to create a better codebase and also allow for older versions than 1.16

You can view the future releases and what's planned for them [here](https://github.com/ReportCardsMC/SKNoise/projects/1)

---

## Expressions

### Perlin Noise

​	**Info** This expression is an expression to get a noise value at a given x, y, and z. There is no changers to this.

​	**Pattern** `[sknoise] perlin noise [at] [x ]%number%[,] [[y ]%number%[,] [[z ]%number%]]`

​	**Returns** (Number) A number between *-1* and *1*

​	**Example** 

```vb
set {_noise} to perlin noise at {_x} / 50, {_y}, {_z} / 50
set block at location({_x}, {_noise} * 40, {_z}) to bedrock
```

### Simplex Noise

​	**Info** This expression is an expression to get a noise value at a given x, y, and z. There is no changers to this.

​	**Pattern** `[sknoise] simplex noise [at] [x ]%number%[,] [[y ]%number%[,] [[z ]%number%]]`

​	**Returns** (Number) A number between *-1* and *1*

​	**Example** 

```vb
set {_noise} to simplex noise at {_x} / 50, {_y}, {_z} / 50
set block at location({_x}, {_noise} * 40, {_z}) to bedrock
```

### Seed

​	**Info** The seed to the perlin generator or the simplex generator. Allows setting, adding, removing, and resetting

​	**Pattern** `(perlin|simplex) [noise] seed`

​	**Returns** (Number) The seed of the generator. (Perlin and Simplex are two separate seeds)*

​	**Example** 

```vb
send "%perlin seed%" to player
set simplex seed to {_seed}
add 1 to perlin seed
```

### Average

​	**Info** Get's an average of the given numbers

​	**Pattern** (average|avg) of %numbers%

​	**Returns** (Number) Average of numbers

​	**Example** 

```vb
send "%average of 5, 10, 15, 20%" # Returns 12.5
```

### 
