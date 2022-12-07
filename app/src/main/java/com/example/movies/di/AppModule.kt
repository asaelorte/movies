package com.example.movies.di

import android.app.Application
import androidx.room.Room
import com.example.data.repository.PermissionChecker
import com.example.data.source.LocalDataSource
import com.example.data.source.LocationDataSource
import com.example.data.source.RemoteDataSource
import com.example.movies.R
import com.example.movies.data.AndroidPermissionChecker
import com.example.movies.data.PlayServicesLocationDataSource
import com.example.movies.data.database.MovieDatabase
import com.example.movies.data.database.RoomDataSource
import com.example.movies.data.server.TheMovieDbDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    @Named("apiKey")
    fun apiKeyProvider(app: Application): String = "0d260853196016de62fbf13a3d77eaf0"

    @Provides
    @Singleton
    fun databaseProvider(app: Application) = Room.databaseBuilder(
        app,
        MovieDatabase::class.java,
        "movie-db"
    ).build()

    @Provides
    fun localDataSourceProvider(db: MovieDatabase): LocalDataSource = RoomDataSource(db)

    @Provides
    fun remoteDataSourceProvider(): RemoteDataSource = TheMovieDbDataSource()

    @Provides
    fun locationDataSourceProvider(app: Application): LocationDataSource =
        PlayServicesLocationDataSource(app)

    @Provides
    fun permissionCheckerProvider(app: Application): PermissionChecker =
        AndroidPermissionChecker(app)
}