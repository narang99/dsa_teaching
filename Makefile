.PHONY: clean
# we are doing file date checking in the python program itself
create: code_theme.theme
	python3 scripts/mkHtmlTree.py

code_theme.theme:
	pandoc --print-highlight-style tango > code_theme.theme

clean:
	rm code_theme.theme
	rm -r html
	rm -r markdown/codes