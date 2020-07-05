import pandas as pd
import time
from itertools import product
from joblib import Parallel,delayed
import numpy as np
import warnings
warnings.filterwarnings('ignore')
from warnings import catch_warnings,filterwarnings
from dateutil.relativedelta import relativedelta
import json
import statsmodels as sm
from statsmodels.graphics import tsaplots as tsa
from datetime import datetime
from statsmodels.tsa.stattools import adfuller
import matplotlib.pylab as plt
from statsmodels.graphics.tsaplots import plot_acf, plot_pacf
from statsmodels.tsa.arima_model import ARIMA
from statsmodels.tsa.statespace.sarimax import SARIMAX
from statsmodels.tsa.seasonal import seasonal_decompose

df = pd.read_csv('outcome.csv')
df_json = df.to_json(orient='split')
df_json2=json.loads(df_json)
with open('outcome1.json','w') as file_obj:
    json.dump(df_json2,file_obj)

df = pd.read_csv('outcome.csv')
df_json = df.to_json()
df_json2=json.loads(df_json)
with open('outcome12.json','w') as file_obj:
    json.dump(df_json2,file_obj)

# df2 = pd.read_json('outcome.json')
# df2.to_csv('outcome2.csv')
