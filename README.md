
# The movie Data Base API 

Dagger, Room, ViewModel, lifecycle, RxJava, Retrofit, Glide v4, JUnit.

## Persistencia

1. Mapeo base de datos: VideoEntity, MoviesEntity, CreditsEntity.
2. Objeto acceso base de datos: VideoDAO, MoviesDAO, CreditsDAO. 
3. Base de datos aplicación: DataBaseTMDB.

## Red

1. Consumir API REST: MoviesApi.
2. Petición asíncrona: NetworkDetails, NetworkMain.

## Repositorio

1. Manejo de datos: DetailsRepository, MainRepository.

## ViewModel

1.Preparar y proporcionar los datos para la interfaz de usuario: MainViewModel, DetailsViewModel.

## Vista

1. Interacción con el usuario : DetailsActivity, ReclyclerViewAdapterDetaills, MainActivity, ReclyclerViewAdapterMain

## Inyección de dependencias

1. foldel di.

## JUnit

1. Probar inserción y consulta de base de datos:  TestDataBase

## Principios de responsabilidad única

Una clase o componente debe tener una funcionalidad bien definida, de tal manera que sea responsable del comportamiento de una parte del software. 

## Código limpio

•	Contiene los principios de responsabilidad única
•	Debe ser fácil de entender y mantener, sus métodos y clases deben expresar la responsabilidad o función definida.
•	No debe tener código repetitivo
•	Se debe poder acoplar y reutilizar con otros módulos. 

![image 1 ](https://github.com/pepgear350/TheMovieDB/blob/master/image1.jpeg)
![image 2 ](https://github.com/pepgear350/TheMovieDB/blob/master/image2.jpeg)


# License

Copyright 2019 PepGear

Licensed to the Apache Software Foundation (ASF) under one or more contributor license agreements. See the NOTICE file distributed with this work for additional information regarding copyright ownership. The ASF licenses this file to you under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.



