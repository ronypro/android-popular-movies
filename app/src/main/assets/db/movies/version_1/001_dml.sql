CREATE TABLE movie (
    _id INTEGER PRIMARY KEY,
    poster_path TEXT,
    original_title TEXT,
    overview TEXT,
    vote_average REAL,
    release_date INTEGER
);

CREATE TABLE review (
    _id INTEGER PRIMARY KEY AUTOINCREMENT,
    movie_id INTEGER,
    api_id TEXT,
    author TEXT,
    content TEXT,

    CONSTRAINT review_movie_id FOREIGN KEY (movie_id) REFERENCES movie (_id) ON DELETE CASCADE
);

CREATE TABLE video (
    _id INTEGER PRIMARY KEY AUTOINCREMENT,
    movie_id INTEGER,
    api_id TEXT,
    name TEXT,
    site TEXT,
    site_key TEXT,

    CONSTRAINT video_movie_id FOREIGN KEY (movie_id) REFERENCES movie (_id) ON DELETE CASCADE
);
