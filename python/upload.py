import pandas as pd
import firebase_admin
from firebase_admin import db
from firebase_admin import firestore
import datetime
import random



cred_obj = firebase_admin.credentials.Certificate(R"big-screen-uwl-firebase-adminsdk-90lgx-290a4a4212.json")
default_app = firebase_admin.initialize_app(cred_obj)

db = firestore.client()



movies = pd.read_csv("data/mymoviedb.csv", nrows=20)
print(movies.head())

ref = db.collection(u'movies')







NUM_FEATURED = 6

MOVIE_SCREENS = ["A", "B", "C", "D", "E", "F"]
MOVIE_TIMES_DATETIMES = [
    datetime.datetime(2022, 12, day, hour, minute, 0) for day in range(1, 31) for hour in range(10, 22, 3) for minute in [0, 30]

]

for i, row in movies.iterrows():
    print(i)
    update, new_ref = ref.add({
        'title' : row["Title"],
        'descriptionShort' : row["Overview"][:30],
        'descriptionLong' : row["Overview"],
        'rating' : row["Vote_Average"],
        'imageUri' : row["Poster_Url"],
        'isFeatured' : i < NUM_FEATURED

    })
    # randomly assign 10 screenings to each movie
    for x in range(10):
        random_screen = random.choice(MOVIE_SCREENS)
        random_time = random.choice(MOVIE_TIMES_DATETIMES)

        new_ref.collection(u'screenings').add({
            'screen' : random_screen,
            'dateTime' : random_time
        })






# for each movie in firebase collection, 





