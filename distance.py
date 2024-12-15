import numpy as np
import pandas as pd

data = pd.read_csv('TemplateCopy.csv')
print(data.columns)
#['End Address', 'Driving Distance (meters)', 'Driving Distance (miles)']
cleanedData = data[data['Driving Distance (miles)'] < 5]
result = cleanedData['End Address']
print(result)

result.to_csv('addressesToCater.csv', index = None)

