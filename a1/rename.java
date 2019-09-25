import java.util.*;
import java.lang.*;
import java.io.*;

public class rename {
    public static void main(String args[]) {
        // first check that the format of command line parameters are correct

        ArrayList<String> filenames = new ArrayList<String>();
        if (args.length == 0) {
            System.out.println("Program was not given at least one option with valid arguments and one filename");
            printHelp();
        } else {
            // parse args
            filenames = parse(args);
        }

        // compute the renaming
        rename_files(filenames, args);
    }

    static void printHelp() {
        System.out.println("(c) 2019 Nathan Hung. Last revised: Sept 15, 2019.");
        System.out.println("Usage: java rename [-option argument1 argument2 ...]\n");
        System.out.println("Options:");
        System.out.println("-help\t:: display this help");
        System.out.println("-prefix [string]\t:: rename the file by prepending [string] to the filename");
        System.out.println("-suffix [string]\t:: rename the file by appending [string] to the filename");
        System.out.println("-replace [str1] [str2]\t:: rename all files with name [str1] with [str2]");
        System.out.println("-file [filename]\t:: denotes the [filename] to be modified");
    }

    // parse args, if errors occur, then print error messages and exit program here too
    static ArrayList<String> parse(String[] args) {
        ArrayList<String> filenames = new ArrayList<String>();
        String key = null;
        // booleans to check whether any errors encountered
        boolean parsing_error = false;
        boolean filename_found = false;
        boolean valid_option_found = false;
        boolean help_found = false;

        // process each commandline input as either an option or argument
        for (int i = 0; i < args.length; i++) {
            // assume that options start with a dash
            if (args[i].startsWith("-")) {
                if (args[i].equals("-help")) {
                    help_found = true;
                } else if (args[i].equals("-prefix")) {
                    i++;
                    if (i < args.length && !args[i].startsWith("-")) {
                        while (i < args.length && !args[i].startsWith("-")) {
                            i++;
                        }
                        i--; // reached either end of array or seen another option, so adjust here
                        valid_option_found = true;
                    } else {
                        System.out.println("Insufficient arguments supplied to -prefix");
                        parsing_error = true;
                        i--;
                        continue;
                    }
                } else if (args[i].equals("-suffix")) {
                    i++;
                    if (i < args.length && !args[i].startsWith("-")) {
                        while (i < args.length && !args[i].startsWith("-")) {
                            i++;
                        }
                        i--; // reached either end of array or seen another option, so adjust here
                        valid_option_found = true;
                    } else {
                        System.out.println("Insufficient arguments supplied to -suffix");
                        parsing_error = true;
                        i--;
                        continue;
                    }
                } else if (args[i].equals("-replace")) {
                    if (i + 2 < args.length) {
                        // get the argument for prefix
                        if ((args[i + 1].startsWith("-")) || (args[i + 1].startsWith("-"))) {
                            System.out.println("Arguments cannot start with a \"-\"");
                            parsing_error = true;
                        } else {
                            i += 2;
                            valid_option_found = true;
                        }
                    } else {
                        System.out.println("Insufficient arguments supplied to -replace");
                        parsing_error = true;
                    }
                } else if (args[i].equals("-file")) {
                    i++;
                    if (i < args.length) {
                        // get the argument for prefix
                        if (args[i].startsWith("-")) {
                            System.out.println("Argument cannot start with a \"-\"");
                            parsing_error = true;
                        } else {
                            filenames.add(args[i]);
                            filename_found = true;
                        }
                    } else {
                        System.out.println("Insufficient arguments supplied to -file");
                        parsing_error = true;
                    }
                } else {
                    System.out.println("Invalid option specified");
                    System.out.println(args[i]);

                }
                // values start with anything else
            } else {
                // invalid number of options or invalid # of arguments given to option or no option was given
                // TODO: check whether the string is a file in the curr directory, if so output "no options provided"
                System.out.println("Options must start with a \"-\"");
            }
        }

        if (help_found == true) {
            printHelp();
            if (parsing_error == true || valid_option_found == false || filename_found == false) {
                System.exit(1);
            }
            System.exit(0);
        }
        if (valid_option_found == false) {
            System.out.println("No valid option provided");
            if (filename_found == false) {
                System.out.println("No valid filename provided");
            }
            printHelp();
            System.exit(1);
        } else if (filename_found == false) {
            System.out.println("No valid filename provided");
            printHelp();
            System.exit(1);
        }
        if (parsing_error == true) {
            System.exit(1);
        }
        return filenames;
    }

    static void rename_files(ArrayList<String> filenames, String[] args) {
        //Set entrySet = options.entrySet();
        //Iterator it = entrySet.iterator();


        // sb.replace
        // sb.insert(0, ___);
        // sn.append(_____);
        // later: just create an arraylist of all filenames
        for (int i = 0; i < filenames.size(); i++) {
            String updated_file_name = filenames.get(i);
            for (int j = 0; j < args.length; j++) {
                //System.out.println(options.get("-file").get(i));

                if (args[j].equals("-prefix")) {
                    j++;
                    StringBuilder temp = new StringBuilder();
                    while (j < args.length && !args[j].startsWith("-")) {
                        temp.append(args[j]);
                        j++;
                    }
                    j--; // reached either end of array or seen another option, so adjust here
                    updated_file_name = temp + updated_file_name;
                } else if (args[j].equals("-suffix")) {
                    j++;
                    StringBuilder temp = new StringBuilder();
                    while (j < args.length && !args[j].startsWith("-")) {
                        temp.append(args[j]);
                        j++;
                    }
                    j--; // reached either end of array or seen another option, so adjust here
                    updated_file_name = updated_file_name + temp;
                } else if (args[j].equals("-replace")) {
                    updated_file_name = updated_file_name.replaceAll(args[j + 1], args[j + 2]);
                    j += 2;
                } else {
                    // ignore
                }
            }
            // update file name here
            System.out.println(updated_file_name);
            File f_old = new File(filenames.get(i));
            File f_new = new File(updated_file_name);
            if (!f_old.exists()) {
                System.out.println("Filename does not exist");
            }
            if (f_old.isDirectory()) {
                System.out.println("Filename is a directory"); //????
            }
            try {
                boolean result = f_old.renameTo(f_new);
                    if (result == true) {
                        System.out.println("Filename " + filenames.get(i) + " renamed to " + updated_file_name);
                    } else {
                        System.out.println("Failed to rename " + filenames.get(i));
                    }
            } catch (Exception ex) {
                System.out.println(ex.toString());
            }
        }
    }
}