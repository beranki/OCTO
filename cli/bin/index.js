#!C:/Users/anjan/node
import _yargs from "yargs";
import { hideBin } from "yargs/helpers";
import terminalImage from "terminal-image";
import chalk from "chalk";
import child_process from 'child_process';
import terminalLink from 'terminal-link';
import chalkAnimation from 'chalk-animation';
import * as emoji from 'node-emoji';

const yargs = _yargs(hideBin(process.argv));

const error = chalk.bold.red;
const warning = chalk.hex('#FFA500');

const usage = (chalk.hex("#A866EE")("\nUsage: run [--option] [<args>]"));
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
                                    alias: "file-api",
                                    describe: chalk.blue("View rules for lab file formats."),
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
        child_process.exec('run --help', (error, stdout, stderr) => {
            if (error) {
                console.error(`exec error: ${error}`);
                return;
            }

            console.log(chalk.green("" + `${stdout}`));
        });
    }, 3000);
}
