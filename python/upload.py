import pandas as pd
import firebase_admin
from firebase_admin import db
from firebase_admin import firestore



cred_obj = firebase_admin.credentials.Certificate(R"big-screen-uwl-firebase-adminsdk-90lgx-290a4a4212.json")
default_app = firebase_admin.initialize_app(cred_obj)

db = firestore.client()



movies = pd.read_csv("data/mymoviedb.csv", nrows=20)
print(movies.head())

ref = db.collection(u'movies')





NUM_FEATURED = 6

for i, row in movies.iterrows():
    print(i)
    ref.add({
        'title' : row["Title"],
        'descriptionShort' : row["Overview"][:30],
        'descriptionLong' : row["Overview"],
        'rating' : row["Vote_Average"],
        'imageUri' : row["Poster_Url"],
        'isFeatured' : i < NUM_FEATURED

    })





