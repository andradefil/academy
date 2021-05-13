#include <stdio.h>
#include <stdlib.h>
#include <curses.h>

#define CLEAR			"\x1B[2J"
#define C_LEFT		"0x68"
#define C_RIGHT		"0x6c"
#define C_UP			"\x1B[A"
#define C_DOWN		"\x1B[B"

#define ARROW_LEFT		75
#define ARROW_RIGHT		77
#define ARROW_UP			72
#define ARROW_DOWN		80
#define DOUBLE_H			205
#define DOUBLE_V			186


int main() {
  initscr();
	int key;

  while((key = getch()) != '\n') {
	};

	return 0;
};

