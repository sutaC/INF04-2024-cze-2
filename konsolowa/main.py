#!/bin/python

class Album:
    def __init__(self) -> None:
        self.artist: str
        self.album: str
        self.songsNumber: int 
        self.year: int
        self.downloadNumber: int

# **********************************************
# nazwa funkcji: readAlbums
# opis funkcji: Wczytuje albumy z zapisanego pliku
# parametry: brak
# zwracany typ i opis: list[Albums] - Lista wczytanych albumów
# autor: XYZ
# ***********************************************
def readAlbums() -> list[Album]:
    albums = list[Album]()
    with open("./Data.txt", "r", encoding="utf-8") as file:
        while True:
            album = Album()
            album.artist = file.readline().strip()
            if len(album.artist) == 0: # Checks does file has more content
                break
            album.album = file.readline().strip()
            album.songsNumber = int(file.readline().strip())
            album.year = int(file.readline().strip())
            album.downloadNumber = int(file.readline().strip())
            albums.append(album)
            file.readline() # Skips whitespace
    return albums

def displayAlbums(albums: list[Album]):
    for album in albums:
        print(album.artist)
        print(album.album)
        print(album.songsNumber)
        print(album.year)
        print(album.downloadNumber)
        print() # Whitespace

# Start
albums = readAlbums()
displayAlbums(albums)
