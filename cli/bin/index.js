#!C:/Users/anjan/node
import _yargs from "yargs";
import { hideBin } from "yargs/helpers";
import terminalImage from "terminal-image";
import chalk from "chalk";
import child_process from 'child_process';
import terminalLink from 'terminal-link';
import chalkAnimation from 'chalk-animation';
import glob from "glob";
import objectTreeify from "object-treeify";
import lodash from "lodash";
import { createInterface } from "readline";

const yargs = _yargs(hideBin(process.argv));

const error = chalk.bold.red;
const warning = chalk.hex('#FFA500');

const usage = (chalk.hex("#A866EE")("\nUsage: octo [--option] [<args>]"));
const img = await terminalImage.file("static/test_title.png");

const options = yargs.usage(usage)
                      .option({
                               "o": {
                                    alias: "home",
                                    describe: chalk.green("Home - view options + load utils"),
                                    demandOption: false
                                },
                                "b": {
                                    alias: "branch", 
                                    describe: chalk.greenBright("Visualize all custom written scripts \nin a branch based format."),
                                    type:"boolean", 
                                    demandOption: false 
                                },
                                "x": {
                                    alias: "sandbox",
                                    describe: chalk.red("Enter script name to run a command line based simulation."),
                                    type: "string",
                                    demandOption: false
                                },
                                "l": {
                                    alias: "labs",
                                    describe: chalk.red("Enter lab-file path and script name to determine script's accuracy."),
                                    type: "array",
                                    demandOption: false
                                },
                                "a": {
                                    alias: "api",
                                    describe: chalk.blue("View code documentation + lab file formats."),
                                    type: "boolean",
                                    demandOption: false
                                },
                                "f": {
                                    alias: "fsm",
                                    describe: chalk.blue("A rudimentary finite state machine creation tool."),
                                    type: "string",
                                    demandOption: false
                                },
                                "m": {
                                    alias: "mapping",
                                    describe: chalk.blue("Manage current Hardware Mappings in a GUI based control. "),
                                    type: "boolean",
                                    demandOption: false
                                }
                              })
                              .help(true)
                              .argv;

if (yargs.usage(usage).argv.o != null) {
    console.log(img);
    console.log(chalk.redBright("A CONSOLE CLI DESIGNED BY BHARGAV ERANKI - 2023 "));
    const karaoke = chalkAnimation.karaoke("PLEASE STAR THE REPO ⭐");
    karaoke.start();

    setTimeout(() => {
        child_process.exec('octo --help', (error, stdout, stderr) => {
            if (error) {
                console.error(`exec error: ${error}`);
                return;
            }

            console.log(chalk.green("" + `${stdout}`));
        });
    }, 3000);
}

if (yargs.usage(usage).argv.b != null) {
    var gd = function (src, callback) {
        glob(src + '/**/*', callback);
    }

    gd('scripts', function (err, res) {
        if (err) {
            console.log("error", err);
        } else {

            const treeify = (paths) => objectTreeify(paths.reduce((p, c) => {
                lodash.set(p, c.match(/[^/]+/g));
                return p;
              }, {}));
            
            console.log(chalk.blue(treeify(res)));
        }
    });
}

if (yargs.usage(usage).argv.m != null) {
    if (yargs.usage(usage).argv.g != null) {
        drawMockHub();
        console.log("\n*Attempt to replicate REV Robotics Control/Expansion Hub (depending on mapping ID). NOT EXACT, but as close as you can get in a terminal...I think.");
    } else if (yargs.usage(usage).argv.p != null) {
        console.log('sample');
    }
}



function drawMockHub() {
    let stringToDisplay = ""
    let w = 30, h = 25;

    let motorPorts = 0;
    let servoPorts = 0;
    let analogPorts = 0;
    let digitalPorts= 0;
    let I2CPorts = 0;
    let pwmPorts = 0;

    for (let i = 0; i < h; i++) {
        stringToDisplay += "\t";

        if (i % 4 == 0 && i >= 8 && motorPorts <= 3) {
            stringToDisplay += "MTR.pt" + chalk.greenBright("(" + (motorPorts++) + ")\t");
        } else {
            stringToDisplay += "\t\t";
        }
        for (let j = 0; j < w; j++) {
            if (i == 0 || i == h-1 || (i == h-4 && j > 4 && j < w - 5)) {
                stringToDisplay += chalk.bgGray("--");
            } else if (j == 0 || j == w - 5 || j == 4) {
                if ((i >= 8 && i % 4 == 0 && j < 4) || (j == w-5 && i %5 != 0 && i < h-7)) {
                    stringToDisplay += chalk.bgGray("L") + chalk.gray("_");
                } else {
                    if (i == h-4 && j != 0 && j != w-5) {
                        stringToDisplay += chalk.bgGray("| ");
                    } else {
                        stringToDisplay += chalk.bgGray("|") + ((j == 4 && i < h - 4) ? chalk.bgHex("#FFA500")(" ") : " ");
                    }
                }
            } else if (j == w-1 && (j > w-5 && i % 5 != 0 && i < h - 7)) {
                stringToDisplay += chalk.gray("_") + chalk.bgGray("|");
            } else if (j == w-1) {
                stringToDisplay += " " + chalk.bgGray("|");
            } else if ((j >= 5 && j <= w-8 && j % 3 == 0 && i > h - 4)) {
                stringToDisplay +=  " " + chalk.bgGray("|");
            } else if (i >= 8 && i % 4 == 0 && j < 5 || (j > w-5 && i % 5 != 0 && i < h - 7)) {
                stringToDisplay += chalk.gray("__");
            } else {
                if (i < h - 4 && (j > 4 && j < w - 5)) {
                    stringToDisplay += chalk.bgHex("#FFA500")("  ");
                } else {
                    stringToDisplay += "  ";
                }
            }
        }

        stringToDisplay += "\t"
        
        if (i < 5 && i >= 1) {
            stringToDisplay += chalk.greenBright("(" + (pwmPorts++) + ")") + "PWM.pt";
        }

        if (i >= 6 && i < 10) {
            stringToDisplay += chalk.greenBright("(" + (I2CPorts++) + ")") + "I2C.pt";
        }

        if (i >= 11 && i < 15) {
            stringToDisplay += chalk.greenBright("(" + (digitalPorts++) + "-" + (digitalPorts++) + ")") + "DIG.pt";
        }

        if (i >= 16 && i < 18) {
            stringToDisplay += chalk.greenBright("(" + (analogPorts++) + "-" + (analogPorts++) + ")") + "ALG.pt";
        }

        stringToDisplay += "\n";
    }

    stringToDisplay += "\t\t\t\t\t";
    for (let j = 0; j < w; j++) {
        if (j <= w-8 && j % 3 == 1 && servoPorts <= 5) {
            stringToDisplay += chalk.greenBright("(" + (servoPorts++) + ")   ");
        }
    }
    servoPorts = 0;
    stringToDisplay += "\n\t\t\t\t\t---------------------------------"
    stringToDisplay += "\n\t\t\t\t\t\t\t|" +
                        "\n\t\t\t\t\t\t   SRV.pts(0-5)";

    console.log(stringToDisplay);
}
