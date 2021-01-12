from pathlib import Path
import subprocess

mdRoot = Path("./markdown")
htmlRoot = Path("./html")
codesRoot = Path("./codes")
mdCodesRoot = mdRoot / "codes"
luaFilter = Path("./scripts/mdToHtml.lua")

def globInList(exts, root):
    ans = []
    for ext in exts:
        ans.extend(list(root.glob("**/*" + ext)))
    return ans

def getFilesOfExtRelTo(exts, root):
    allFilesRelativeToMakeFile = globInList(exts, root)
    allFiles = [f.relative_to(root)
        for f in allFilesRelativeToMakeFile]
    return allFiles

def getCodePrefix(path):
    extToSuffix = {
        ".c": "cpp",
        ".py": "python",
        ".ts": "typescript",
        ".java": "java",
        ".cpp": "cpp",
        ".js": "javascript"
    }
    if path.suffix in extToSuffix:
        suffix = extToSuffix[path.suffix]
    else:
        suffix = ""
    return f"```{suffix}\n"

def getCodeSuffix(path):
    return "\n```"
    
def executorOnlyIfModifiedOrNotExists(callback, src_root, dest_root,
                dest_suffix):
    def curried(src_path_):
        dest_path_ = src_path_.with_suffix(dest_suffix) 
        src_path, dest_path = src_root/src_path_, dest_root/dest_path_
        if (not dest_path.exists() or 
            dest_path.stat().st_mtime < src_path.stat().st_mtime):
            if not dest_path.parent.exists():
                dest_path.parent.mkdir(exist_ok=True, parents=True)
            callback(src_root, src_path, dest_root, dest_path)
            return True
        return False
    return curried

def runCommand(command):
    process = subprocess.Popen(command,
                        stdout=subprocess.PIPE, 
                        stderr=subprocess.PIPE)
    stdout, stderr = process.communicate()
    return (stdout, stderr)

def pandoc_runner(src_root, src_path, dest_root, dest_path):
    command = f"pandoc -s --highlight-style code_theme.theme -f markdown -t html5 {str(src_path)} -o {str(dest_path)} --lua-filter={str(luaFilter)}"
    runCommand(command.split(' '))

mdToHtml = executorOnlyIfModifiedOrNotExists(pandoc_runner,
                    mdRoot, htmlRoot, '.html')

def convertToMd(src_root, src_path, dest_root, dest_path):
    with open(dest_path, "a") as writer:
        writer.write(getCodePrefix(src_path))
        with open(src_path, "r") as reader:
            writer.writelines(reader.readlines())
        writer.write(getCodeSuffix(src_path))

codeToMd = executorOnlyIfModifiedOrNotExists(convertToMd,
                    codesRoot, mdRoot / "codes", ".md")

if __name__ == "__main__":
    codeFiles = getFilesOfExtRelTo([".c", ".java", ".py", ".ts", ".js", ".cpp"], codesRoot)
    rebuilt = []
    for f in codeFiles:
        if codeToMd(f):
            rebuilt.append(str(f))
    print("\nmodified code files\n")
    if(len(rebuilt) > 0):
        for name in rebuilt:
            print(name)

    mdFiles = getFilesOfExtRelTo([".md"], mdRoot)
    rebuilt = []
    for f in mdFiles:
        if mdToHtml(f):
            rebuilt.append(str(f))
    print("\nmodified md files:\n")
    if(len(rebuilt) > 0):
        for name in rebuilt:
            print(name)