import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {User} from '../model/user';
import {Observable} from 'rxjs';
import {Role} from "../model/role";

@Injectable()
export class UserService {

  private usersUrl: string;

  constructor(private http: HttpClient) {
    this.usersUrl = 'http://localhost:8080/users';
  }

  public findAll(): Observable<User[]> {
    return this.http.get<User[]>(this.usersUrl);
  }
  public findAllRoles(): Observable<Role[]> {
    return this.http.get<Role[]>(this.usersUrl+'/roles');
  }
  findAllRolesByUserId(id: string):Observable<Role[]> {
    return this.http.get<Role[]>(this.usersUrl+'/roles');
  }
  public save(user: User) {
    return this.http.post<User>(this.usersUrl, user);
  }

  public delete(user: User) {
    return this.http.delete(this.usersUrl + "/" + user.id);
  }

  public edit(user: User) {
    let url = this.usersUrl + "/" + user.id;
    return this.http.put<User>(url, user);
  }

  getUserById(id: number): Observable<User> {
    return this.http.get<User>(this.usersUrl + "/" + id);
  }


}



