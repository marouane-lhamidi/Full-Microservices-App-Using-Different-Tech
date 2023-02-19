import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {Product} from "../../model/Product.model";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";
import {SecurityService} from "../keycloak/security.service";

@Injectable({
  providedIn: 'root'
})
export class ProductService{
  products!: Product[];
  basedUrl = environment.api_url + "PRODUCT-SERVER/products";

  constructor(private http: HttpClient, private securityService:SecurityService) {

  }

  public getProducts() : Observable<Product[]>{
    return this.http.get<Product[]>(this.basedUrl, {headers:{"Authorization":"Bearer "+this.securityService.kcService.getKeycloakInstance().token}});
  }

  public handleProductDelete(id: number) : Observable<void>{
    return this.http.delete<void>(`${this.basedUrl}/${id}`, {headers:{"Authorization":"Bearer "+this.securityService.kcService.getKeycloakInstance().token}});
  }

  addNewProduct(product: Product): Observable<Product> {
    product.id= 0;
    return this.http.post<Product>(`${this.basedUrl}`, product, {headers:{"Authorization":"Bearer "+this.securityService.kcService.getKeycloakInstance().token}});

  }
  editNewProduct(product: Product): Observable<Product> {
    return this.http.put<Product>(`${this.basedUrl}/${product.id}`, product, {headers:{"Authorization":"Bearer "+this.securityService.kcService.getKeycloakInstance().token}});
  }

}
