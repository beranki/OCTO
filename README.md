![test_title](https://github.com/beranki/OCTO/assets/77950550/c6f62b47-e3c6-4c53-a754-843e6b1e3fd5)

created by **Bhargav Eranki**, 2023. 
- developed for Cupertino Robotics testing purposes.
- aims to emulate FIRST software package and aims to automate the process of FTC software labs without the compile time.
- design inspired by piobots 7128 mascot, "the problem child"

octo runs based off of custom CLI coupled with a web deployed API. <br>
_project remains in progress_, and will actively be developed throughout the training and development season. 

-----------------------
Usage: octo [option] [args]

Options: <br>
  -o, --home     Home - view options + load utils <br>
  -b, --branch   Visualize all custom written scripts <br>
                 in a branch based format.                              [boolean] <br>
                 
  -x, --sandbox  Enter script name to run a command line based simulation. <br>
                                                                        [string] <br>
  -l, --labs     Enter lab-file path and script name to determine script's accur <br>
                 acy.                                               [array] <br>
  -a, --api      View code documentation + lab file formats. [boolean] <br>
  -f, --fsm      A rudimentary finite state machine creation tool. <br>
                                                                        [string] <br>
  -m, --mapping  Manage current Hardware Mappings in a GUI based control. <br>
                                                                       [boolean] <br>
      --help     Show help                                             [boolean] <br>
  
Implemented using Node.js for CLI + API backend, Svelte for API front-end, Java for mock software package. 

---------------------
Setup:
