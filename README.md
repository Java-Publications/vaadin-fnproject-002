
<center>
<a href="https://vaadin.com">
 <img src="https://vaadin.com/images/hero-reindeer.svg" width="200" height="200" /></a>
</center>


# FnProject - How we could start with it?

## installation of the FnProject
The installation is short and easy.
Assuming that Docker is already installed and running
we need only the FnProject Utils for convenience.

``` 
curl -LSs https://raw.githubusercontent.com/fnproject/cli/master/install | sh
```

This should run on any Linux/Unix based Operating System.

After the binaries are downloaded from the Internet, and the path is set 
the local version of the FnProject environment could be started with the command line command
```fn start```

Now it is time for the first Java code.

## first Java Function
To start with a java project the project boilerplate generator
should be used. Switch into the folder that is planned to be the root of your function-code.
On command line execute the following command. 

```fn init --runtime=java --name your_dockerhub_account/hello```
 
This will create the boilerplate code that can be used as a starting point.
The generation process will create a Hello-World Function and a corresponding jUnit test, 
based on jUnit4.

### The Function itself
The Hello World Function is a plain old Java-Method.
Nothing special, no inheritance, no added framework.

```java
package org.rapidpm.fnproject.helloworld;

public class HelloFunction {

    public String handleRequest(String input) {
        String name = (input == null || input.isEmpty()) ? "world"  : input;
        return "Hello, " + name + "!";
    }
}
```

### func.yml
Here we are writing all the meta - information's down, for the 
runtime environment. The only thing that needs a short 
explanation is the **cmd** part. The value is 
the method reference to the initial method that will be executed.
As you can see, it is the full qualified classname 
with the methodname. That's it... Only one more thing..  The default is a JDK9.

```yaml
name: vaadin-fnproject-001
version: 0.0.2
runtime: java
cmd: org.rapidpm.fnproject.helloworld.HelloFunction::handleRequest
build_image: fnproject/fn-java-fdk-build:jdk9-1.0.59
run_image: fnproject/fn-java-fdk:jdk9-1.0.59
format: http
```

With every deployment, the version number will be increased automatically 
from the system.

### first run
Now we have all things together to run the function on a locale Docker instance.
First of all you should set the environment variable **FN_REGISTRY** to your 
docker-hub username you want to use for this. ```export FN_REGISTRY=fndemouser```

To run the function, the command line script fn could be used.
To build the image, use ```fn build```. First invocation will need some time, 
because the runtime images are downloaded from the network.

A following ```fn run``` will start and invoke the function once.
This function can handle a missing input, to provide some input data
the pipe can be used. ```echo -n "Ohhh no" | fn run```

### deploy and run
Time to deploy the function, so that it could be consumed regularly.
The target will be started locally with the command ```fn start```.
The command will download the server image and start it.

Target for now, is the local Docker Host. this will lead to 
an additionally command param. ```--local```
The command itself contains three parts.

* deploy 
* --local , to use the local Docker Host
* --app , to define a logical application name

Here the example will be deployed local and under the name **vaadin-fnproject-001**  
```fn deploy --local --app vaadin-fnproject-001```


After the deployment, the version number will be automatically increased, even if the deployment fails.

### test via command line
A deployed function can be tested with a http request. The command **curl** will be your friend.
The URL to request is build by the static part **http://localhost:8080/r/** , 
if you are running on a local Docker Host, and the **app-name** plus **function-name**
In this case the URL will be 
```curl http://localhost:8080/r/vaadin-fnproject-001/vaadin-fnproject-001```

What will be clear after this first step, the naming scheme is imported, right from the beginning.


Happy Coding.

if you have any questions: ping me on Twitter [https://twitter.com/SvenRuppert](https://twitter.com/SvenRuppert)
or via mail.