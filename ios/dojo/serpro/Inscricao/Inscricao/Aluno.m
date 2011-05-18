
//
//  Aluno.m
//  Inscricao
//
//  Created by Cleverson Sacramento on 18/05/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import "Aluno.h"


@implementation Aluno

@synthesize nome;
@synthesize cpf;

- (id)initWithNome:(NSString *) aNome andCpf: (NSString *) aCpf {
    self = [super init];
    if (self) {
        self.nome = aNome;
        self.cpf = aCpf;
    }
    
    return self;
}

- (BOOL)isEqual:(id)other {
    if(other == self){
        return TRUE;
    }
    
    if([self.cpf isEqual:((Aluno *)other).cpf] ){
        return TRUE;
    }
    
    return FALSE;
}

@end
