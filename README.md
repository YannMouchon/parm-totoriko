# parm-totoriko

**Tiny ARM Cortex-M0 simulation using Logisim.**

## Presentation

*Stands for Polytech ARM - Totoriko (our team name)*.

A small hardware architecture project given during our first semester at Polytech Nice Sophia Antipolis.

Here we try design a small ARM Cortex-M0 processor and its assembler, which can only execute a small subset of Thumb-1 instructions.
We use Logisim for the electronic simulation part, and ANTLR and its Java API for the software part.

## Organization

In the logisim-project directory, you will find all of our logisim files for the microprocessor, but also all the unit testing files that are available for our components.

You can find a parser in the src/main directory. It converts some ARM-V7 Thumb-1 instructions into hexadecimal that can directly be read by Logisim.

Finally, in this repository, the presentation file we used for our oral examination can also be found.

## Usage

Maven is used to compile, test, run, and package the project.

The assembler produces a text file Logisim can read.

```shell script
git clone https://github.com/lahmbil/parm-totoriko.git
cd parm-totoriko

mvn clean package
mvn exec:java -Dexec.args="--input /path/to/assembly/file --output outputfile" # using maven to run

# to package the application
mvn clean compile assembly:single
# then
java -jar target/parm-totoriko-1.0.jar -i /path/to/assembly/file -o outputfile
```

We used Logisim **2.15** with the **-analyze** flag.
Load Machine.circ. The previously generated text file can now be loaded in the ROM.