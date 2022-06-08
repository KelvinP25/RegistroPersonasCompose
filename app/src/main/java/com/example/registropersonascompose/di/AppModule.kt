package com.example.registropersonascompose.di

import android.content.Context
import androidx.room.Room
import com.example.registropersonascompose.data.OcupacionDao
import com.example.registropersonascompose.data.PersonaDao
import com.example.registropersonascompose.data.RegistroPersonasComposeDb
import com.example.registropersonascompose.data.Repository.OcupacionRepository
import com.example.registropersonascompose.data.Repository.PersonaRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesRegistroPersonasComposeDb(@ApplicationContext context: Context): RegistroPersonasComposeDb {
        return Room.databaseBuilder(
            context, RegistroPersonasComposeDb::class.java, "RegistroPersonasComposeDb"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideOcupacionDao(registroPersonasComposeDb: RegistroPersonasComposeDb): OcupacionDao =
        registroPersonasComposeDb.ocupacionDao

    @Provides
    fun providePersonaDao(registroPersonasComposeDb: RegistroPersonasComposeDb): PersonaDao =
        registroPersonasComposeDb.personaDao

    @Provides
    fun providePersonaRepository(personaDao: PersonaDao): PersonaRepository{
        return PersonaRepository(personaDao)
    }
    @Provides
    fun provideOcupacionRepository(ocupacionDao: OcupacionDao): OcupacionRepository{
        return OcupacionRepository(ocupacionDao)
    }

}