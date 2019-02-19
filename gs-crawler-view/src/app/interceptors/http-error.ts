import { Injectable } from '@angular/core';
import { HttpRequest, HttpInterceptor, HttpHandler, HttpEvent, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable()
export class ErrorHandlerInterceptor implements HttpInterceptor {

    constructor() { }

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

        return next.handle(request).pipe(
            catchError((err: HttpErrorResponse) => {
                switch (err.status) {
                    case 404:
                        console.error('Página não encontrada', err.status.toString());
                        break;
                    case 500:
                        console.error('Erro interno de servidor', err.status.toString());
                        break;
                }
                return throwError(err);
            })
        );
    }
}
