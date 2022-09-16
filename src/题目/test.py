orderNum = input()
prices = input().split(" ")
menu= {"SS": int(prices[0])/2,"S":int(prices[0]), "M":int(prices[1]),"L":int(prices[2]),"LL":int(prices[2])*2 }
result = 0
for i in range(int(orderNum)):
    order = input()
    result += menu.get(order)

print(int(result))