//
//  Inscricao.m
//  Inscricao
//
//  Created by Cleverson Sacramento on 18/05/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import "Constants.h"
#import "Inscricao.h"
#import "SalaCheiaException.h"
#import "AlunoDuplicadoException.h"


@implementation Inscricao

- (id)init {
    self = [super init];
    if (self) {
        inscritos = [[NSMutableArray alloc] init];
    }
    return self;
}

-(BOOL)estaInscrito:(Aluno *) aluno {
    return [inscritos containsObject:aluno];
}

-(void)cadastrar:(Aluno *) aluno {
    if([self salaLotada]) {       
        @throw [[SalaCheiaException alloc] initWithLotacao:[inscritos count]];
    }
    
    if([self estaInscrito:aluno]) {
        @throw [[AlunoDuplicadoException alloc] initWithAluno:aluno];
    }
    
    [inscritos addObject:aluno];
}

- (BOOL)salaLotada {
    return [inscritos count] == LOTACAO_SALA;
}

- (void)dealloc {
    [inscritos release];
    [super dealloc];
}

@end
