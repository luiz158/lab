//  MonitoramentoManagerTest.m

#import "MonitoramentoManagerTest.h"
#import "MonitoramentoManager.h"

@implementation MonitoramentoManagerTest

- (void)testInserirMonitoramentoComSucesso
{
    Monitoramento *monitoramento = [[Monitoramento alloc] init];
    monitoramento.objeto = @"PB882615209BR";
    monitoramento.email = @"cleverson.sacramento@gmail.com";
    
    BOOL sucesso = [MonitoramentoManager inserir:monitoramento];
    
    STAssertTrue(sucesso, @"O resultado tem que ser verdade");
}

- (void)testObterMonitoramentosComSucesso
{
    NSArray *monitoramentos = [MonitoramentoManager obterPorEmail:@"cleverson.sacramento@gmail.com"];
    
    for (Monitoramento *monitoramento in monitoramentos) {
        NSLog(@"objeto: %@\n", monitoramento.objeto);
        NSLog(@"email : %@\n", monitoramento.email);
    }
    
    STAssertTrue(monitoramentos.count > 0, @"É preciso ter monitoramentos cadastrados");
}

- (void)testExcluirMonitoramentoComSucesso
{
    Monitoramento *monitoramento = [[Monitoramento alloc] init];
    monitoramento.objeto = @"PB882615209BR";
    monitoramento.email = @"cleverson.sacramento@gmail.com";
    
    BOOL sucesso = [MonitoramentoManager excluir:monitoramento];
    
    STAssertTrue(sucesso, @"O resultado tem que ser verdade");
}

@end
