import re

a = "'」asasdasdasd'"
b = "'asasdasdasd'"
print(a[1::])
if "「" in a or "」" in a:
    print("包含")
else:
    print("不包含")
w = 1
w += 2
w = 4

strs = ["VAR45  0", "VAR453 = 0", "VAR45 = 0", "VAR451  1"]

for str in strs:
    if re.search('VAR\d*  \d', str):
        strs[strs.index(str)]= str.replace("  "," = ")
print(strs)