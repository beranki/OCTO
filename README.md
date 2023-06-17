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
  **-v**, --version   Show version number                                                   [boolean] <br>
  **-o**, --home      Home - view options + load utils                                      [boolean] <br>
  **-b**, --branch    Visualize all custom written script in a branch based format.         [boolean] <br>
  **-x**, --sandbox   Enter script name to run a command line based simulation.             [string] <br>
  **-l**, --labs      Enter lab-file path and script name to determine script's accuracy.   [array] <br>
  **-a**, --file-api  View rules for lab file formats.                                      [boolean] <br>
  **-h**, --help      Show help                                                             [boolean] <br>
  
Implemented using Node.js for CLI + API backend, Svelte for API front-end, Java for mock software package. 

---------------------
Setup:
