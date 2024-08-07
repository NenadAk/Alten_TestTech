
## Consignes

Développer un back-end permettant la gestion de produits définis plus bas. Vous pouvez utiliser la technologie de votre choix parmis la list suivante :

- nodejs/express
- Java/Spring Boot
- C#/.net Core
- Python/Flask

Le back-end doit gérer les API REST suivantes : 

| Resource           | POST                  | GET                            | PATCH                                    | PUT | DELETE           |
| ------------------ | --------------------- | ------------------------------ | ---------------------------------------- | --- | ---------------- |
| **/products**      | Create a new products | Retrieve all products          | X                                        | X   |     X            |
| **/products/1**    | X                     | Retrieve details for product 1 | Update details of product 1 if it exists | X   | Remove product 1 |

Un produit a les caractéristiques suivantes : 

``` typescript
class Product {
  id: number;
  code: string;
  name: string;
  description: string;
  price: number;
  quantity: number;
  inventoryStatus: string;
  category: string;
  image?: string;
  rating?: number;
}
```

## Informations générales

## Technologies utilisés

- Java/Spring Boot
- Base de données : H2

## Structure backend

### Model
#### Product
Déclaration des caractéristiques d'un produit en leur associant le type de variable (Integer id, String code, String name, String description, String image, Integer price, String category, Integer quantity, String inventoryStatus, Integer rating)
+ Constructeurs
+ Getters & Setters

#### User
- [ ] à faire pour le systeme d'autentification (admin, client etc.)


### Services
#### DBInit
Ce service remplit la base de données H2 avec les données du fichier json fournit ("/data/products.json") en utilisant le modèle ProductModel.

#### ProductServices
Ce fichier regroupe tous les services en rapport avec les objets utilisant ProductModel, notamment en faisant le lien vers la Repository.

#### User
- [ ] à faire pour le systeme d'autentification (admin, client etc.)

### Repositories
#### ProductRepository
Interface Repository reprenant les fonctionctionnalités CRUD pour l'entité Product coté base de données.

#### User
- [ ] à faire pour le systeme d'autentification (admin, client etc.)

### Controllers
#### ProductController
Controller de l'API Rest où on gère les fonctionnalités CRUD, les redirections et la securité.


### Autres
ProductNotFoundException = Objet permettant de personnaliser une RuntimeException



## Base données H2

Console de la BDD : localhost:3000/h2-console  
JDBC URL : jdbc:h2:mem:tempdb  
User Name: alten  
Password:  

### Paramétrage dans application.properties
```
spring.datasource.url=jdbc:h2:mem:tempdb
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=alten
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
spring.h2.console.enabled=true

```
