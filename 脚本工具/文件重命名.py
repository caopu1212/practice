import os
Root_dir = r"G:\汉化\R2\改后缀"
files = os.listdir(Root_dir)
print(files)	#list型
print(type(files))
count = 1
for file in files:
    newname_token = []
    newname_token.append(file[:-3])
    count += 1
    if count <= 4:
        print("newname_token",newname_token)
    newfilename = ''.join(newname_token) + 'ADV'
    if count <= 4:
        print("newfilename",newfilename)
    #join的作用是将list连接成一个字符串, list=[1,2,3], "-".join(list) => 1-2-3
    old_path = os.path.join(Root_dir,file)
    new_path = os.path.join(Root_dir,newfilename)
    os.rename(old_path,new_path)