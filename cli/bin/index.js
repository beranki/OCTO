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
import fs from "fs";
import { createInterface } from "readline";

const readline = createInterface({
    input: process.stdin,
    output: process.stderr
})

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
                                    describe: chalk.blue("View and manage hardware mappings: -g (graphically), -p (plain), -e(edit)"),
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
    try {
        const data = fs.readFileSync("pkg/util/mappings.txt", 'utf8');
        let lines = data.split("\r\n");

        let numMotors = 4;
        let numServos = 6;
        let numDigDev = 4;
        let numPWM = 4;
        let numAnalog = 2;
        let numI2C = 4;

        let map = new Map();
        map.set('motor', []);
        map.set('servo', []);
        map.set('pwm', []);
        map.set('i2c', []);
        map.set('digital', []);
        map.set('analog', []);
    

        let tag = lines[0].replace("!", "");
        //console.log(tag);
        for (let i = 1; i < lines.length; i++) {
            if (lines[i].length > 0) {
                switch (lines[i]) {
                    case '@motors':
                        i++;
                        for (let j = i; j < i+numMotors; j++) {
                            let elems = lines[j].trim().split("|");
                            if (elems.length > 1) {
                                //console.log(elems);
                                //console.log(lines[j].substring());
                                let name = elems[0].substring(1).trim();
                                let type = elems[1].trim();
                                //console.log(name," -> ", type);
                                map.get('motor').push(name + " -> " + type);
                                i++;
                                //console.log(map);
                            }
                        }
                        break;
                    case '@servos':
                        i++;
                        for (let j = i; j < i + numServos; j++) {
                            let elems = lines[j].trim().split("|")
                            if (elems.length > 1) {
                                //console.log(elems);
                                //console.log(lines[j].substring());
                                let name = elems[0].substring(1).trim();
                                let type = elems[1].trim();
                                //console.log(name," -> ", type);
                                map.get('servo').push(name + " -> " + type);
                                i++;
                            }
                            //console.log(map);
                        }
                        break;
                    case '@digital_devices':
                        i++;
                        for (let j = i; j < i + numDigDev; j++) {
                            let elems = lines[j].trim().split("|")
                            if (elems.length > 1) {
                                //console.log(elems);
                                //console.log(lines[j].substring());
                                let name = elems[0].substring(3).trim();
                                let type = elems[1].trim();
                                //console.log(name," -> ", type);
                                map.get('digital').push(name + " -> " + type);
                                i++;
                            }
                            //console.log(map);
                        }
                        break;
                    case '@pwm_devices':
                        i++;
                        for (let j = i; j < i + numPWM; j++) {
                            let elems = lines[j].trim().split("|");
                            if (elems.length > 1) {
                                //console.log(elems);
                                //console.log(lines[j].substring());
                                let name = elems[0].substring(1).trim();
                                let type = elems[1].trim();
                                //console.log(name," -> ", type);
                                map.get('pwm').push(name + " -> " + type);
                                i++;
                            }
                            //console.log(map);
                        }
                        break;
                    case '@analog_input_devices':
                        i++;
                        for (let j = i; j < i + numAnalog; j++) {
                            let elems = lines[j].trim().split("|");
                            if (elems.length > 1) {
                                //console.log(elems);
                                //console.log(lines[j].substring());
                                let name = elems[0].substring(3).trim();
                                let type = elems[1].trim();
                                //console.log(name," -> ", type);
                                map.get('analog').push(name + " -> " + type);
                                i++;
                            }
                            //console.log(map);
                        }
                        break;
                    case '@i2c':
                        i++;
                        for (let j = i; j < i + numI2C; j++) {
                            let elems = lines[j].trim().split("|")
                            if (elems.length > 1) {
                                //console.log(elems);
                                //console.log(lines[j].substring());
                                let name = elems[0].substring(1).trim();
                                let type = elems[1].trim();
                                //console.log(name," -> ", type);
                                map.get('i2c').push(name + " -> " + type);
                                i++;
                            }
                            //console.log(map);
                        }

                        break;
                    default:
                    
                }
            }
        }

            drawMockHub(map, tag);
            promptChange(map, tag);
            

    } catch (err) {
        console.log(err);
    }
}


/*
    Draws mock hub w/ mappings and all open ports. does not detail device types, only which ports they occupy. requires ANSI support for color.
*/
function drawMockHub(map, hub_id) {
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
            if (map.get("motor").length > motorPorts) {
                let a = map.get("motor")[motorPorts].split(" ")[0] + chalk.redBright("(" + (motorPorts++) + ")");
                if (a.length <= 16) {
                    stringToDisplay += (a + "\t\t\t");
                } else {
                    stringToDisplay += (a + "\t\t");
                } 
            } else {
                stringToDisplay += "MTR.pt" + chalk.greenBright("(" + (motorPorts++) + ")\t\t");
            }
        } else {
            stringToDisplay += "\t\t\t";
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
                    if (i >= h/2 - hub_id.length/2 && i < h/2 + hub_id.length/2 && j == 5) {
                        stringToDisplay += chalk.bgHex("#FFA500")(chalk.black(hub_id.charAt(i - (h/2 - hub_id.length/2)))) + chalk.bgHex("#FFA500")(" ");
                    } else {
                        stringToDisplay += chalk.bgHex("#FFA500")("  ");
                    }
                } else {
                    stringToDisplay += "  ";
                }
            }
        }

        stringToDisplay += "\t"
        
        if (i < 5 && i >= 1) {
            if (map.get('pwm').length > pwmPorts) {
                stringToDisplay += chalk.redBright("(" + (pwmPorts++) + ")") + map.get('pwm')[pwmPorts-1];
            } else {
                stringToDisplay += chalk.greenBright("(" + (pwmPorts++) + ")") + "PWM.pt";
            }
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

function listMappings(map, hub_id) {
    let b1 = "├─";
    let b2 = "└─";
    let b3 = "│";

    let stringToDisplay = "";

    stringToDisplay += (b1 + " " + hub_id + "\n");
    map.forEach(function(val, key)  {
        let title = b3 + ' '.repeat(hub_id.length/2) + b2 + " " + key;
        stringToDisplay += title + "\n";
        val.forEach(element => {
           stringToDisplay += (b3 + ' '.repeat(title.length*3/4) + b2 + " " + element + "\n");
        });
    });

    console.log(stringToDisplay);
}

async function promptChange(map, tag) {
    while (true) {
        //listMappings(map, tag);
        
        const answer = await new Promise(resolve => {
            readline.question("(A)dd, (E)dit, (R)emove mappings, E(X)it? -> ", resolve);
        });
        
        if (answer.toLowerCase().trim() == "a") {
            const portName = await new Promise(resolve => {
                readline.question("Enter port name (i.e. MTR 2, SRV 0) -> ", resolve);
            });

            let data = portName.split(" ");
            if (data.length <= 1) {
                console.log("portName format invalid");
            } else {
                let num = portName.split(" ")[1].trim();
                let type = portName.split(" ")[0].trim();
                
                const deviceName = await new Promise(resolve => {
                    readline.question("Enter device name -> ", resolve);
                });

                const deviceType = await new Promise(resolve => {
                    readline.question("Enter device type -> ", resolve);
                });

                switch (type) {
                    case "MTR":
                        if (deviceType == "DcMotor" || deviceType == "CRServo") {
                            map.get('motor').push(deviceName.trim() + " -> " + deviceType.trim());
                        } else {
                            console.log("invalid device type for motor port");
                        }
                        break;
                    case "SRV":
                        if (deviceType == "Servo") {
                            map.get('servo').push(deviceName.trim() + " -> " + deviceType.trim());
                        } else {
                            console.log("invalid device type for servo port");
                        }
                        break;
                    case "PWM": //NOT IMPLEMENTED
                        continue;
                    case "I2C":
                        if (deviceType == "Color Sensor" || deviceType == "Distance Sensor") {
                            map.get('i2c').push(deviceName.trim() + "->" + deviceType.trim());
                        } else {
                            console.log("invalid device type for i2c port");
                        }
                        break;
                    case "DIG":
                        if (deviceType == "LED") {
                            map.get('digital').push(deviceName.trim() + " -> " + deviceType.trim());
                        } else {
                            console.log("invalid device type for digital port")
                        }
                        break;
                    case "ALG":
                        if (deviceType == "Potentiometer") {
                            map.get('analog').push(deviceName.trim() + " -> " + deviceType.trim());
                        } else {
                            console.log("invalid device type for analog port")
                        }
                        break;
                    default:
                        console.log("invalid device type");
                }
            }
        } else if (answer.toLowerCase().trim() == 'e') {

        } else if (answer.toLowerCase().trim() == 'r') {

        } else if (answer.toLowerCase().trim() == 'x') {
            listMappings(map, tag);
            writeMap(map, tag);
            return;
        }
    }
}

function writeMap(map, tag) {
    try {
        const data = fs.readFileSync("pkg/util/mappings.txt", 'utf8');
        let lines = data.split("\r\n");

        let numMotors = 4;
        let numServos = 6;
        let numDigDev = 4;
        let numPWM = 4;
        let numAnalog = 2;
        let numI2C = 4;

        let map = new Map();
        map.set('motor', []);
        map.set('servo', []);
        map.set('pwm', []);
        map.set('i2c', []);
        map.set('digital', []);
        map.set('analog', []);
    

        let tag = lines[0].replace("!", "");
        //console.log(tag);
        for (let i = 1; i < lines.length; i++) {
            if (lines[i].length > 0) {
                switch (lines[i]) {
                    case '@motors':
                        i++;

                
                        break;
                    case '@servos':
                        i++;
                       
                        break;
                    case '@digital_devices':
                        i++;
                        
                        break;
                    case '@pwm_devices':
                        i++;
                       
                        break;
                    case '@analog_input_devices':
                        i++;
                        
                        break;
                    case '@i2c':
                        i++;

                        break;
                    default:
                    
                }
            }
        }
    } catch (err) {

    };
}