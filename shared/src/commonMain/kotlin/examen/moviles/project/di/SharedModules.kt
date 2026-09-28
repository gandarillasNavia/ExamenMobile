package examen.moviles.project.di

import org.koin.core.module.Module


fun sharedModules(): List<Module> = listOf(
    dataModule,
    presentationModule,
    domainModule
)
