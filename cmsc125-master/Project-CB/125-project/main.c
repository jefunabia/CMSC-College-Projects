#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <ctype.h>
#include <dirent.h>
#include <stdint.h>
#include <time.h>
#include <math.h>
#include <wchar.h>
#include <libgen.h>
#include <windows.h>
#include <sys/stat.h>

#define TOKEN_SIZE 128

// List of commands
char *built_in_str[] = {
    "\t!cd\t: Displays the name of or changes the current directory.",
    "\t!chdir\t: Changes the current directory.",
    "\t!cls\t: Clears the screen.",
    "\t!cmd\t: Starts a new instance of the command interpreter.",
    "\t!copy\t: Copies one or more files to another location.",
    "\t!date\t: Displays or sets the date.",
    "\t!del\t: Deletes one or more files.",
    "\t!dir\t: Displays a list of files and subdirectories in a directory.",
    "\t!exit\t: Exits the terminal.",
    "\t!help\t: Displays the full list of commands.",
    "\t!mkdir\t: Creates a directory.",
    "\t!move\t: Moves one or more files from one directory to another directory.",
    "\t!rename\t: Renames a file or files.",
    "\t!rmdir\t: Removes a directory.",
    "\t!time\t: Displays or sets the system time.",
    "\t!type\t: Displays the contents of a text file."
};

/* --------------------------------
*  ------- ERROR MESSAGES ---------
*  --------------------------------
*/
// Display syntax error message
void err_syntax() {
	printf("Invalid syntax.\n");
}

// Display file not found error message
void err_file_not_found() {
	printf("The system cannot find the path specified.\n");
}

/* --------------------------------
*  ------- HELPER FUNCTIONS -------
*  --------------------------------
*/
// Total number of commands
int num_built_in = sizeof(built_in_str) / sizeof(char *);

// Display the list of commands
int display_help(){
    printf("\n");
    printf(" Shell commands: \n");
    printf("\n");

    for (int i = 0; i < num_built_in; i++) {
        printf("%s\n",built_in_str[i]);
    }

    printf("\n");
    return 1;
}

// Display initialization text
void init_shell(){

    printf("\n\n\n\n******************"
        "*****************************************");
    printf("\n-----------------------------------------------------------");
    printf("\n\n\n\t\t [ WELCOME ] ");
    printf("\n\n\n\t Enter '!help' to see the list of commands.");
    printf("\n\n\n\n-----------------------------------------------------------");
    printf("\n*******************"
        "****************************************");

    printf("\n");
    sleep(1);

}

// Display current directory
void display_current_directory() {
	char cwd[1024];
	getcwd(cwd, sizeof(cwd));
	printf(cwd);
	printf("\n");
	printf(">");
}

// Read input
char *read_input(){

	char *line = NULL;
	ssize_t bufsize = 0;
	getline(&line, &bufsize, stdin);

	return line;
}

// Convert string to lower case
char *str_lower(char *str) {
	for(int i = 0; str[i]; i++){
	  str[i] = tolower(str[i]);
	}

	return str;
}

// Count number of slashes
int count_slash(char* string) {


    int size = strlen(string);
	int slashes = 0;
	for(int i = 0; i < size; i++) {
		if(string[i] == '\\') {
			slashes++;
		}
	}
	return slashes;
}

// Get size of string
int str_size(char *string) {

	return sizeof(string);
}
// Convert string into array of strings
char **tokenize(char *line, char *delimeters) {
	int bufsize = TOKEN_SIZE;
	int position = 0;
	char **tokens = malloc(bufsize * sizeof(char*));
	char *token;

	if(!tokens) {
		fprintf(stderr, "allocation error\n");
		exit(EXIT_FAILURE);
	}

	token = strtok(line, delimeters);
	while(token != NULL) {
		tokens[position] = token;
		position++;

		if(position >= bufsize) {
			bufsize += TOKEN_SIZE;
			tokens = realloc(tokens, bufsize * sizeof(char*));
			if(!tokens) {
				fprintf(stderr, "allocation error\n");
				exit(EXIT_FAILURE);
			}
		}

		token = strtok(NULL, delimeters);
	}
	tokens[position] = NULL;
	return tokens;
}

// Count number of arguments in a token
int count_args(char **tokens) {
	int noOfArgs = -1;
	char **temp = tokens;

	while(*temp != NULL) {
		noOfArgs++;
		temp++;
	}
	return noOfArgs;
}

int str_characters(char *string) {
	int i = 0;
	while(1) {
		if(string[i] != '\0') {
			i++;
		} else {
			break;
		}
	}

	return i;
}

// Display overwrite message
void msg_overwrite(char *directory) {
	char full[_MAX_PATH];
	_fullpath(full, directory, _MAX_PATH);
	printf("Overwrite %s? (Yes / No / All): ", full);
}

/* --------------------------------
*  ------- CHECKER FUNCTIONS -------
*  --------------------------------
*/
// Check if path is to a directory or file
// 0 if the path is to a dir
// 1 if the path is to a file
int check_path(char *path) {
	struct stat statbuf;
	stat(path, &statbuf);
	if(S_ISDIR(statbuf.st_mode)){
	    return 0;
	}
	else{
	    return 1;
	}
}

// Check if string follows correct time format
int check_time_format(char *string) {
	char **tokens = tokenize(string, ":");
	struct tm breakdown = {0};

	if(tokens[0] && isdigit(*tokens[0])) {
		breakdown.tm_hour = *tokens[0];
		if(tokens[1]) {
			if(isdigit(*tokens[1])) {
				breakdown.tm_min = *tokens[1];
				if(tokens[2]) {
					if(isdigit(*tokens[2])) {
						breakdown.tm_sec = *tokens[2];
					} else {
						return 0;
					}
				}
			} else {
				return 0;
			}
		}
	} else {
		return 0;
	}

	return mktime(&breakdown);
}

// Check if string follows correct date format
int check_date_format(char *string) {
	char **tokens = tokenize(string, "-/");
	struct tm breakdown = {0};

	if(tokens[0] && isdigit(*tokens[0])) {
		breakdown.tm_mon = *tokens[0];
		if(tokens[1]) {
			if(isdigit(*tokens[1])) {
				breakdown.tm_yday = *tokens[1];
				if(tokens[2]) {
					if(isdigit(*tokens[2])) {
						breakdown.tm_year = *tokens[2];
					} else {
						return 0;
					}
				}
			} else {
				return 0;
			}
		}
	} else {
		return 0;
	}

	return mktime(&breakdown);
}


/* --------------------------------
*  --------- CLI FUNCTIONS --------
*  --------------------------------
*/
// cd or chdir
int cmd_cd(char **tokens) {
	int noOfArgs = count_args(tokens);

	if(noOfArgs == 0) {
		char cwd[1024];
		getcwd(cwd, sizeof(cwd));
		printf(cwd);
		printf("\n");
	} else if(noOfArgs > 1) {
		err_file_not_found();
	} else {
		if(chdir(tokens[1]) == -1) {
			err_file_not_found();
		}
	}

	return 1;
}

// cls
int cmd_cls(HANDLE hConsole) {

	COORD coordScreen = { 0, 0 };
	DWORD cCharsWritten;
	CONSOLE_SCREEN_BUFFER_INFO csbi;
	DWORD dwConSize;

	if( !GetConsoleScreenBufferInfo( hConsole, &csbi )) {
	  return 1;
	}

	dwConSize = csbi.dwSize.X * csbi.dwSize.Y;

	if( !FillConsoleOutputCharacter( hConsole, (TCHAR) ' ', dwConSize,
		coordScreen, &cCharsWritten )) {
	  return 1;
	}

	if( !GetConsoleScreenBufferInfo( hConsole, &csbi )) {
	  return 1;
	}

	if( !FillConsoleOutputAttribute( hConsole, csbi.wAttributes, dwConSize,
		coordScreen, &cCharsWritten )) {
	  return 1;
	}
	SetConsoleCursorPosition( hConsole, coordScreen );

	return 1;
}


// cmd
int cmd_cmd() {

	DWORD dwVersion = 0;
    DWORD dwMajorVersion = 0;
    DWORD dwMinorVersion = 0;
    DWORD dwBuild = 0;

    dwVersion = GetVersion();

    dwMajorVersion = (DWORD)(LOBYTE(LOWORD(dwVersion)));
    dwMinorVersion = (DWORD)(HIBYTE(LOWORD(dwVersion)));

    if (dwVersion < 0x80000000)
        dwBuild = (DWORD)(HIWORD(dwVersion));

    printf("Microsoft Windows [Version %d.%d.%d]\n",
                dwMajorVersion,
                dwMinorVersion,
                dwBuild);
	printf("(c) 2019 Microsoft Corporation. All rights reserved.\n");

	return 1;
}

// del
int cmd_del(char **tokens) {
	int i = 1;
	char cwd[1024];

	while(tokens[i] != NULL) {
		if(remove(tokens[i]) == -1) {
			getcwd(cwd, sizeof(cwd));
			if(i == 1) {
				printf("Could Not Find %s\\%s\n", cwd, tokens[i]);
			}
		}
		i++;
	}

	return 1;
}

// copy
int cmd_copy(char **tokens) {
	int noOfArgs = count_args(tokens);
	int filesCopied = 0;
	FILE *fp1;
	FILE *fp2;
	char **filetokens;
	char *filename;
	int fileidx;
	char c;
	char *line;
	char cwd[1024];
	getcwd(cwd, sizeof(cwd));
	char *dir = malloc(100);

	if(noOfArgs == 0 ) {
		err_syntax();
		return 1;
	} else if(noOfArgs == 1) {
		printf("The file cannot be copied onto itself.\n");
		return 1;
	} else if (noOfArgs > 2) {
		for(int i = 1; i < noOfArgs; i++) {
			if(check_path(tokens[i]) == 0) {
				err_syntax();
			} else if(check_path(tokens[i]) == 1) {
				if((fp1 = fopen(tokens[i], "r")) == NULL) {
					err_file_not_found();
				} else {
					if(check_path(tokens[noOfArgs]) == 0) {
						filetokens = tokenize(tokens[i], "\\");
						fileidx = count_args(filetokens);
						filename = filetokens[fileidx];
						strcpy(dir, tokens[noOfArgs]);
						strcat(dir, "\\");
						strcat(dir, filename);
						fp2 = fopen(dir, "r");
						if(fp2 == NULL) {
							fp2 = fopen(dir, "w");
							while((c = getc(fp1)) != EOF) {
								if(fputc(c, fp2) == -1) {
									filesCopied--;
									break;
								}
							}
							filesCopied++;
						} else {
							while(1) {
								msg_overwrite(dir);
								chdir(cwd);
								line = str_lower(read_input());
								if(strstr(line, "yes") != NULL ||
								   strstr(line, "y")  != NULL ||
								   strstr(line, "all") != NULL||
								   strstr(line, "a") != NULL) {
									remove(tokens[i]);
									fp2 = fopen(dir, "w");
									while((c = getc(fp1)) != EOF) {
										fputc(c, fp2);
									}
									filesCopied++;
									break;
								} else if(strstr(line, "no") != NULL ||
										  strstr(line, "n") != NULL) {
									break;
								} else {
									continue;
								}
							}
						}
						free(dir);
						fclose(fp1);
						fclose(fp2);
					} else if(check_path(tokens[noOfArgs]) == 1) {
						err_syntax();
					} else {
						err_syntax();
					}
				}

			} else {
				err_syntax();
				return 1;
			}
		}
		free(dir);
		fclose(fp1);
		fclose(fp2);
		printf("\t%d file(s) copied.", filesCopied);
	} else {
		if((fp1= fopen(tokens[1], "r")) == NULL) {
			err_file_not_found();
			return 1;
		} else {
			check_path(tokens[2]);
			fp2 = fopen(tokens[2], "r");
			if(fp2 == NULL) {
				if(check_path(tokens[2]) == 0) {
					strcat(tokens[2], "\\");
					strcat(tokens[2], tokens[1]);
					cmd_copy(tokens);
				} else if(check_path(tokens[2]) == 1){
					fp2 = fopen(tokens[2], "w");
					while((c = getc(fp1)) != EOF) {
						if(fputc(c, fp2) == -1) {
							filesCopied--;
							break;
						}
					}
					filesCopied++;
					printf("\t%d file(s) copied.", filesCopied);
				} else {
					printf("\t%d file(s) copied.", filesCopied);
				}
			} else {
				while(1) {
					msg_overwrite(tokens[2]);
					chdir(cwd);
					line = str_lower(read_input());
					if(strstr(line, "yes") != NULL ||
					   strstr(line, "y")  != NULL ||
					   strstr(line, "all") != NULL||
					   strstr(line, "a") != NULL) {
						remove(tokens[2]);
						fp2 = fopen(tokens[2], "w");
						while((c = getc(fp1)) != EOF) {
							fputc(c, fp2);
						}
						filesCopied++;
						break;
					} else if(strstr(line, "no") != NULL ||
							  strstr(line, "n") != NULL) {
						break;
					} else {
						continue;
					}
				}
				printf("\t%d file(s) copied.", filesCopied);
			}
		}
	}
	fclose(fp1);
	fclose(fp2);
	return 1;
}

// date
int cmd_date(char **tokens) {
	int noOfArgs = count_args(tokens);
	time_t t = time(NULL);
	struct tm tm = *localtime(&t);
	char *line;
	if(noOfArgs == 0) {
		printf("The current date is: %d-%d-%d\n"
			, tm.tm_year + 1900, tm.tm_mon + 1, tm.tm_mday);
		printf("Enter the new date: (mm-dd-yy)");
		line = read_input();
		if(check_date_format(line)) {
			printf("A required privilege is not held by the client.\n");
		} else {
			printf("The system cannot accept the date entered.\n");
		}
	} else {
		if(noOfArgs > 1) {
			printf("The system cannot accept the date entered.\n");
		} else {
			if(check_date_format(tokens[1])) {
				printf("A required privilege is not held by the client.\n");
			} else {
				printf("The system cannot accept the date entered.\n");
			}
		}
	}
	return 1;
}

// dir
void cmd_dir(DIR *dp, char *opendis) {
    struct dirent *dir;
    dp = opendir(opendis);
    struct stat attr;
    char time[20];
    char size[10];
    char *newsize;
    int length;
    int commas;
    struct tm * timeinfo;
    int files = 0;
    int dirs = 0;
    if (dp) {
    	printf("\n");
        while ((dir = readdir(dp)) != NULL) {
            stat(dir->d_name, &attr);
            timeinfo = localtime (&attr.st_mtime);
            strftime(time, sizeof(time), "%m/%d/%Y %I:%M %p", timeinfo);
            printf("%20s", time);
            if (S_ISDIR(attr.st_mode)) {
                printf("%7s", "<DIR>");
                printf("%15s", " ");
                dirs++;
            }
            else {
                printf("%10s", " ");
                itoa((unsigned)attr.st_size, size, 10);
                length = str_characters(size);
                if(length % 3 == 0) {
                	commas = (length / 3) - 1;
                } else {
                	int mod = length % 3;
                	commas = (length - mod) / 3;
                }
                length = commas + str_characters(size);

                newsize = (char *)malloc(length);

                for(int i = str_characters(size)-1; i >= 0; i--) {
                	if(((str_characters(size)-i)%3 == 0) && i != 0) {
                		newsize[i + commas] = size[i];
                		newsize[i + --commas] = ',';
                	} else {
                		newsize[i + commas] = size[i];
                	}
                }
                newsize[length] = '\0';

                printf("%10s", newsize);
                printf("%2s", " ");
                files++;
            }
            printf("%s\n", dir->d_name);
        }
        closedir(dp);
    }
    else {
    	printf("File not found.\n");
    }
}

// mkdir
int cmd_mkdir(char **tokens) {
	int noOfArgs = count_args(tokens);

	if(noOfArgs < 1) {
		err_syntax();
	} else {
		for(int i = 1; i <= noOfArgs; i++) {
			if(mkdir(tokens[i]) == -1) {
				printf("A subdirectory or file %s already exists.\n", tokens[i]);
				printf("Error occurred while processing: %s.\n", tokens[i]);
			}
		}
	}

	return 1;
}

// move
int cmd_move(char **tokens) {
	int noOfArgs = count_args(tokens);
	FILE *fp1;
	FILE *fp2;
	char **filetokens;
	char *filename;
	int fileidx;
	int filesCopied = 0;
	char c;
	char *line;
	char cwd[1024];
	getcwd(cwd, sizeof(cwd));
	int strsize;
	char *dir = malloc(100);
	char *ptr;

	if(noOfArgs < 2) {
		err_syntax();
	} else {
		for(int i = 1; i < noOfArgs; i++) {
			if(check_path(tokens[i]) == 0) {
				err_syntax();
			} else {
				if(check_path(tokens[noOfArgs]) == 0) {
					filetokens = tokenize(tokens[i], "\\");
					fileidx = count_args(filetokens);
					filename = filetokens[fileidx];
					strcpy(dir, tokens[noOfArgs]);
					strcat(dir, "\\");
					strcat(dir, filename);
					if((fp1 = fopen(tokens[i], "r")) == NULL) {
						err_file_not_found();
					} else {
						fp2 = fopen(dir, "r");
						if(fp2 == NULL) {
							fp2 = fopen(dir, "w");
							while((c = getc(fp1)) != EOF) {
								if(fputc(c, fp2) == -1) {
									filesCopied--;
									break;
								}
							}
							filesCopied++;
							fclose(fp1);
							fclose(fp2);
							remove(tokens[i]);
						} else {
							while(1) {
								msg_overwrite(dir);
								chdir(cwd);
								line = str_lower(read_input());
								if(strstr(line, "yes") != NULL ||
								   strstr(line, "y")  != NULL ||
								   strstr(line, "all") != NULL||
								   strstr(line, "a") != NULL) {
									remove(tokens[i]);
									fp2 = fopen(dir, "w");
									while((c = getc(fp1)) != EOF) {
										fputc(c, fp2);
									}
									filesCopied++;
									fclose(fp1);
									fclose(fp2);
									remove(tokens[i]);
									break;
								} else if(strstr(line, "no") != NULL ||
										  strstr(line, "n") != NULL) {
									break;
								} else {
									continue;
								}
							}

						}
					}
				} else {
					err_syntax();
				}
			}
			free(dir);
		}
	}
	printf("\t%d file(s) moved.", filesCopied);
	return 1;
}

// rename
int cmd_rename(char **tokens) {

	int noOfArgs = count_args(tokens);
	int filesCopied = 0;
	FILE *fp1;
	FILE *fp2;
	char c;
	char *line;
	char cwd[1024];
	getcwd(cwd, sizeof(cwd));

	if(noOfArgs != 2) {
		err_syntax();
	} else {
		fp1 = fopen(tokens[1], "r");
		if(fp1 == NULL) {
			err_file_not_found();
		} else {
			check_path(tokens[2]);
			fp2 = fopen(tokens[2], "r");
			if(fp2 == NULL) {
				if(check_path(tokens[2]) == 0) {
					printf("A duplicate file name exists, or the file\n cannot be found.\n");
				} else if(check_path(tokens[2]) == 1){
					if(count_slash(tokens[2]) > 0) {
						err_syntax();
						return 1;
					}
					fp2 = fopen(tokens[2], "w");
					while((c = getc(fp1)) != EOF) {
						if(fputc(c, fp2) == -1) {
							filesCopied--;
							err_syntax();
							return 1;
						}
					}
					filesCopied++;
					fclose(fp1);
					fclose(fp2);
					remove(tokens[1]);
				} else {
					printf(" \n");
				}
			} else {
				printf("A duplicate file name exists, or the file\n cannot be found.\n");
			}
		}
	}
	fclose(fp1);
	fclose(fp2);

	return 1;
}

// rmdir
int cmd_rmdir(char **tokens) {
	int i = 1;

	while(tokens[i] != NULL) {
		if(rmdir(tokens[i]) == -1) {
			err_file_not_found();
		}
		i++;
	}

	return 1;
}

// time
int cmd_time(char **tokens) {
	int noOfArgs = count_args(tokens);
	time_t t = time(NULL);
	struct tm tm = *localtime(&t);
	char *line;
	char **tokens2;

	if(noOfArgs == 0) {
		printf("The current time is: %d:%d:%d\n",
			tm.tm_hour, tm.tm_min, tm.tm_sec);
		printf("Please enter the new time: ");
		line = read_input();
		if(check_time_format(line)) {
			printf("A required privilege is not held by the client.\n");
		} else {
			printf("The system cannot accept the time entered.\n");
		}
	} else {
		if(noOfArgs > 1) {
			printf("The system cannot accept the time entered.\n");
		} else {
			if(check_time_format(tokens[1])) {
				printf("A required privilege is not held by the client.\n");
			} else {
				printf("The system cannot accept the time entered.\n");
			}
		}
	}

	return 1;
}

// type
int cmd_type(char **tokens) {
	int noOfArgs = count_args(tokens);
	int i = 1;
	int c;

	FILE *fp;

	while(tokens[i] != NULL) {
		fp = fopen(tokens[i], "r");
		if(fp == NULL) {
			err_file_not_found();
			if(noOfArgs > 1) {
				printf("Error occurred while processing: %s\n", tokens[i]);
			}
		} else {
			printf("\n%s\n\n\n", tokens[i]);
			while((c = getc(fp)) != EOF) {
				printf("%c", c);
			}
		}
		i++;
	}
	fclose(fp);
	return 1;
}

// Executes commands
int execute_commands(char **tokens) {
	char *cmd = str_lower(tokens[0]);

	if(strcmp("!cd", cmd) == 0 || strcmp("!chdir", cmd) == 0) {
		return cmd_cd(tokens);
	} else if(strcmp("!cd..", cmd) == 0) {
		tokens[0] = "!cd";
		tokens[1] = "..";
		return cmd_cd(tokens);
	} else if(strcmp("!cls", cmd) == 0) {
		HANDLE hStdout;
		hStdout = GetStdHandle(STD_OUTPUT_HANDLE);
		return cmd_cls(hStdout);
	} else if(strcmp("!cmd", cmd) == 0) {
		return cmd_cmd();
	} else if(strcmp("!copy", cmd) == 0) {
		return cmd_copy(tokens);
	} else if(strcmp("!date", cmd) == 0) {
		return cmd_date(tokens);
	} else if(strcmp("!del", cmd) == 0) {
		return cmd_del(tokens);
	} else if(strcmp("!dir", cmd) == 0) {
		char cwd[1024];
		DIR *d;
		if(count_args(tokens) == 0) {
			printf(getcwd(cwd, sizeof(cwd)));
			d = opendir(getcwd(cwd, sizeof(cwd)));
			cmd_dir(d, ".");
		} else {
			for(int i = 1; i <= count_args(tokens); i++) {
				d = opendir(tokens[i]);
				if(d == NULL) {
					printf("File not found\n");
				} else {
					printf("\nDirectory of %s\n", tokens[i]);
					cmd_dir(d, tokens[i]);
				}
			}
		}
		return 1;
	} else if(strcmp("!mkdir", cmd) == 0) {
		return cmd_mkdir(tokens);
	} else if(strcmp("!move", cmd) == 0) {
		return cmd_move(tokens);
	} else if(strcmp("!rename", cmd) == 0) {
		return cmd_rename(tokens);
	} else if(strcmp("!rmdir", cmd) == 0) {
		return cmd_rmdir(tokens);
	} else if(strcmp("!time", cmd) == 0) {
		return cmd_time(tokens);
	} else if(strcmp("!type", cmd) == 0) {
		return cmd_type(tokens);
	} else if(strcmp("!help", cmd) == 0) {
        return display_help(tokens);
    } else if(strcmp("!exit", cmd) == 0) {
		return 0;
	} else if(strcmp("d:", cmd) == 0 || strcmp("c:", cmd) == 0)  {
		chdir(cmd);
		return 1;
	} else {
		printf("'%s' is not recognized as an internal or external command, operable program or batch file.", tokens[0]);
		return 1;
	}

}

int main() {
	char *line;
	char **tokens;
	char dir;
	int i = 0;
	int keepRunning = 1;

    init_shell();

	while(keepRunning) {
		display_current_directory();
		line = read_input();
		if(strlen(line) == 1) {
			printf("\n");
			continue;
		}
		tokens = tokenize(line, " \t\r\n\a");
		keepRunning = execute_commands(tokens);
		printf("\n");
	}

	return 0;
}
