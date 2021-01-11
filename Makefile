.PHONY: clean
# we are doing file date checking in the python program itself
create:
	python3 scripts/mkHtmlTree.py

clean:
	rm -r html
	rm -r markdown/codes