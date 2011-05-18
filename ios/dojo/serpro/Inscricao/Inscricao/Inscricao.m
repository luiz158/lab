//
//  Inscricao.m
//  Inscricao
//
//  Created by Cleverson Sacramento on 18/05/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

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

-(void)cadastrar:(NSString *) aluno {
    if([inscritos count] > 1){       
        @throw [[SalaCheiaException alloc] initWithLotacao:[inscritos count]];
    }
    
    if([inscritos containsObject:aluno]){
        @throw [[AlunoDuplicadoException alloc] initWithAluno:aluno];
    }
    
    [inscritos addObject:aluno];
}

- (void)dealloc {
    [inscritos release];
    
    [super dealloc];
}

@end
