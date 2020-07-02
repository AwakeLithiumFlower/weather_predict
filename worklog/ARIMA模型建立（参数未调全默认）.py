#
import pandas as pd
import numpy as np
import statsmodels as sm
from datetime import datetime
from statsmodels.tsa.stattools import adfuller
import matplotlib.pylab as plt
from statsmodels.graphics.tsaplots import plot_acf, plot_pacf
from statsmodels.tsa.arima_model import ARIMA
from statsmodels.tsa.arima_model import ARMA
from statsmodels.tsa.seasonal import seasonal_decompose

# In[1]:
# 数据导入
# df=pd.read_csv('2198087.csv',encoding='utf-8')
df = pd.read_csv('maxmin2.csv', encoding='utf-8')
df.index = pd.to_datetime(df.date)
ts = df['tmax']
# print(ts.index)


# 移动平均图
def draw_trend(timeseries, size):
    f = plt.figure(facecolor='white')
    # 对size个数据进行移动平均
    rol_mean = timeseries.rolling(window=size).mean()
    # 对size个数据移动平均的方差
    rol_std = timeseries.rolling(window=size).std()

    timeseries.plot(color='blue', label='Original')
    rol_mean.plot(color='red', label='Rolling Mean')
    rol_std.plot(color='black', label='Rolling standard deviation')
    plt.legend(loc='best')
    plt.title('Rolling Mean & Standard Deviation')
    plt.show()


def draw_ts(timeseries):
    f = plt.figure(facecolor='white')
    timeseries.plot(color='blue')
    plt.show()


# Dickey-Fuller test:
def teststationarity(ts):
    dftest = adfuller(ts)
    # 对上述函数求得的值进行语义描述
    dfoutput = pd.Series(dftest[0:4], index=['Test Statistic', 'p-value', '#Lags Used', 'Number of Observations Used'])
    for key, value in dftest[4].items():
        dfoutput['Critical Value (%s)' % key] = value
    return dfoutput


# 自相关和偏相关图，默认阶数为31阶
def draw_acf_pacf(ts, lags=31):
    f = plt.figure(facecolor='white')
    ax1 = f.add_subplot(211)
    plot_acf(ts, lags=31, ax=ax1)
    ax2 = f.add_subplot(212)
    plot_pacf(ts, lags=31, ax=ax2)
    plt.show()

# In[2]:

ts_log = np.log(ts)
# draw_ts(ts_log)
# draw_ts(ts_log)
# draw_ts(ts)

# diff_12 = ts.diff(12)
# diff_12.dropna(inplace=True)
# diff_12_1 = diff_12.diff(1)
# diff_12_1.dropna(inplace=True)
# draw_acf_pacf(diff_12_1)
# print(teststationarity(diff_12_1))
#
# rol_mean = ts.rolling(window=12).mean()
# rol_mean.dropna(inplace=True)
# ts_diff_1 = rol_mean.diff(1)
# ts_diff_1.dropna(inplace=True)
# print(teststationarity(ts_diff_1))

# decomposition = seasonal_decompose(ts_log, model="additive")
#
# trend = decomposition.trend
# seasonal = decomposition.seasonal
# residual = decomposition.resid

rol_mean = ts_log.rolling(window=12).mean()
rol_mean.dropna(inplace=True)
ts_diff_1 = rol_mean.diff(1)
ts_diff_1.dropna(inplace=True)
ts_diff_2 = ts_diff_1.diff(1)
ts_diff_2.dropna(inplace=True)

# rol_mean = ts.rolling(window=365).mean()
# rol_mean.dropna(inplace=True)
# ts_diff_1 = rol_mean.diff(1)
# ts_diff_1.dropna(inplace=True)
# print(teststationarity(ts_diff_1))
# draw_acf_pacf(ts_diff_1)
# draw_ts(ts_diff_1)

model = ARIMA(ts_diff_2, order=(1,1,1),freq=None)

result_arma = model.fit() #  调参部分

predict_ts = result_arma.predict()
# 一阶差分还原
diff_shift_ts = ts_diff_1.shift(1)
diff_recover_1 = predict_ts.add(diff_shift_ts)
# 再次一阶差分还原
rol_shift_ts = rol_mean.shift(1)
diff_recover = diff_recover_1.add(rol_shift_ts)
# 移动平均还原
rol_sum = ts_log.rolling(window=12).sum()
rol_recover = diff_recover*12 - rol_sum.shift(1)
# 对数还原
log_recover = np.exp(rol_recover)*50
log_recover.dropna(inplace=True)

ts = ts[log_recover.index]  # 过滤没有预测的记录
plt.figure(facecolor='white')
log_recover.plot(color='blue', label='Predict')
ts.plot(color='red', label='Original')
plt.legend(loc='best')
plt.title('RMSE: %.4f'% np.sqrt(sum((log_recover-ts)**2)/ts.size))
plt.show()

# model = ARIMA(ts, order=(1,1,1),freq=None)
#
# result_arma = model.fit(disp=-1) #  调参部分
#
# predict_ts = result_arma.predict()*13
#
# ts = ts[predict_ts.index]  # 过滤没有预测的记录
# plt.figure(facecolor='white')
# predict_ts.plot(color='blue', label='Predict')
# ts.plot(color='red', label='Original')
# plt.legend(loc='best')
# plt.title('RMSE: %.4f'% np.sqrt(sum((predict_ts-ts)**2)/ts.size))
# plt.show()

# In[3]:
# 数据导出
