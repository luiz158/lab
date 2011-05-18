//
//  Inscricao.h
//  Inscricao
//
//  Created by Cleverson Sacramento on 18/05/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "Aluno.h"


@interface Inscricao : NSObject {
    
    NSMutableArray *inscritos;
}

-(void)cadastrar:(Aluno *) aluno;

-(BOOL)estaInscrito:(Aluno *) aluno;

-(BOOL)salaLotada;

@end
