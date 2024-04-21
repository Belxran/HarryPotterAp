package com.example.harrypotterapp.di

import com.example.harrypotterapp.ui.personajes.PersonajesFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(fragment: PersonajesFragment)
}


