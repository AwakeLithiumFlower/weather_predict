import pandas as pd
import numpy as np
from datetime import datetime
import matplotlib.pylab as plt

# 路径加r防止/转义
data = pd.read_csv(r'C:\Users\36334\Desktop\wash.csv', index_col='DATE', parse_dates=['DATE'])
sub = data['1980-06':'1981-12']['TMAX']
train = sub.loc['1980-06':'1980-12']  # loc切分
test = sub.loc['1980-06':'1981-12']
#plt.figure(figsize=(10, 10))
#print(train)
#plt.plot(train)
#plt.show()
# 差分法使数据平稳
data['TMAX_diff_1'] = data['TMAX'].diff(1)
data['TMAX_diff_2'] = data['TMAX_diff_1'].diff(1)
fig = plt.figure(figsize=(20, 6))
ax1 = fig.add_subplot(131)
ax1.plot(data['TMAX'])
ax2 = fig.add_subplot(132)
ax2.plot(data['TMAX_diff_1'])
ax3 = fig.add_subplot(133)
ax3.plot(data['TMAX_diff_2'])
#plt.show()
# 数据拖尾和截尾情况
import statsmodels.api as sm

#fig = plt.figure(figsize=(12, 8))
#ax1 = fig.add_subplot(211)
#fig = sm.graphics.tsa.plot_acf(train, lags=20, ax=ax1)
#ax1.xaxis.set_ticks_position('bottom')
#fig.tight_layout()
#ax2 = fig.add_subplot(212)
#fig = sm.graphics.tsa.plot_pacf(train, lags=20, ax=ax2)
#ax2.xaxis.set_ticks_position('bottom')
#fig.tight_layout()
#plt.show()
# 寻找合适参数




#模型检验

#model = sm.tsa.ARIMA(train, order=(1, 0, 1))
#results = model.fit()
#resid = results.resid #赋值
#fig = plt.figure(figsize=(12,8))
#fig = sm.graphics.tsa.plot_acf(resid.values.squeeze(), lags=40)
#plt.show()
#训练集中拟合

model = sm.tsa.ARIMA(sub, order=(1, 0, 1))

results = model.fit()

predict_sunspots = results.predict(start=str('1980-06'),end=str('2020-01'),dynamic=False)

print(predict_sunspots)

fig, ax = plt.subplots(figsize=(12, 8))

ax = sub.plot(ax=ax)

predict_sunspots.plot(ax=ax)

plt.show()
#训练集外
print("test")
pred = results.predict(start=len(train),end=len(train)+3000,dynamic=True)#预测要从样本内部开始向后
print(pred)
print(len(test))
print(pred[6:-1])
pred.plot()
test.plot()

fig, ax = plt.subplots(figsize=(12, 8))

ax = sub.plot(ax=ax)

pred.plot(ax=ax)
plt.show()

