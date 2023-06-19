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