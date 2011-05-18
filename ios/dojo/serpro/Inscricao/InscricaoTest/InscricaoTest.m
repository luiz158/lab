//
//  InscricaoTest.m
//  InscricaoTest
//
//  Created by Cleverson Sacramento on 18/05/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import "Constants.h"
#import "Aluno.h"
#import "InscricaoTest.h"
#import "Inscricao.h"
#import "AlunoDuplicadoException.h"
#import "SalaCheiaException.h"

@implementation InscricaoTest

- (void)setUp {
    [super setUp];
    inscricao = [[Inscricao alloc] init];
}

- (void)tearDown {
    [inscricao release];
    [super tearDown];
}

- (void)testCadastrarAlunoComSucesso {
    Aluno *aluno = [[Aluno alloc] initWithNome:@"ZyC" andCpf:@"123.456.789-00"];
    
    [inscricao cadastrar:aluno];
    STAssertTrue([inscricao estaInscrito:aluno], nil);
    
    [aluno release];
}

- (void)testCadastrarAlunoDuplicado {
    Aluno *aluno;
    
    aluno = [[Aluno alloc] initWithNome:@"Wilson" andCpf:@"111.222.333-99"];
    [inscricao cadastrar:aluno];
    [aluno release];
    
    aluno = [[Aluno alloc] initWithNome:@"Wilson" andCpf:@"111.222.333-99"];
    @try {
        [inscricao cadastrar:aluno];
        
        STFail(@"Não deveria ter cadastrado o aluno");
    }
    @catch (AlunoDuplicadoException *exception) {
        NSLog(@"%@", [exception reason]);
    }
    [aluno release];
}

-(void)testCadastrarAteOLimiteDaSala {
    Aluno *aluno;
    
    int i = 0;
    while (![inscricao salaLotada]) {
        aluno = [[Aluno alloc] init];
        aluno.nome = [NSString stringWithFormat:@"Aluno %i", i];
        aluno.cpf = [NSString stringWithFormat:@"000.000.000-0%i", i];
        
        [inscricao cadastrar:aluno];
        [aluno release];
        
        i++;
    }
}

-(void)testCadastrarAlemDoLimiteDaSala {
    Aluno *aluno;
    
    int i = 0;
    while (![inscricao salaLotada]) {
        aluno = [[Aluno alloc] init];
        aluno.nome = [NSString stringWithFormat:@"Aluno %i", i];
        aluno.cpf = [NSString stringWithFormat:@"000.000.000-0%i", i];
        
        [inscricao cadastrar:aluno];
        [aluno release];
        
        i++;
    }
    
    aluno = [[Aluno alloc] initWithNome:@"Intruso" andCpf:@"666.666.666-66"];
    @try {
        [inscricao cadastrar:aluno];
        
        STFail(@"Não deveria ter cadastrado o aluno");
    }
    @catch (SalaCheiaException *exception) {
        NSLog(@"%@", [exception reason]);
    }
    [aluno release];
}

@end
