//  MonitoramentoManager.m

#import "MonitoramentoManager.h"

@implementation MonitoramentoManager

+ (BOOL)inserir:(Monitoramento *)monitoramento
{
    // URL do serviço de monitoramento no ambiente de testes (sandbox).
    NSURL *url = [NSURL URLWithString:@"http://services.sandbox.encomendaz.net/monitoring.json"];
    
    // Criando a requisição com o método PUT.
    NSMutableURLRequest *request = [NSMutableURLRequest requestWithURL:url];
    [request setHTTPMethod:@"PUT"];

    // Usando o PUT devemos passar os parâmetros via body exigidos pelo serviço (clientId e trackId).
    NSString *body = [NSString stringWithFormat:@"clientId=%@&trackId=%@", monitoramento.email, monitoramento.objeto];
    [request setHTTPBody:[body dataUsingEncoding:NSUTF8StringEncoding]];

    // Como o serviço exige que os parâmetros sejam passados via FORM...
    [request setValue:@"application/x-www-form-urlencoded" forHTTPHeaderField:@"Content-Type"];
    
    // Submetendo a requisição e obtendo o resultado.
    NSError *error;
    NSData *resultado = [NSURLConnection sendSynchronousRequest:request returningResponse:nil error:&error];
    
    // Verificando a ocorrência de erros HTTP.
    if(error){
        NSLog(@"Erro HTTP: %@", error.description);
        return NO;
    }
    
    // O serviço retorna JSON com o resultado da operação, mas, por enquanto, vou ignorar para facilitar o entedimento.
    NSLog(@"Resultado: %@", [[NSString alloc] initWithData:resultado encoding:NSUTF8StringEncoding]);
    
    return YES;
}

+ (NSArray *)obterPorEmail:(NSString *)email
{
    // URL do serviço de monitoramento no ambiente de testes (sandbox).
    NSString *stringUrl = [NSString stringWithFormat:@"http://services.sandbox.encomendaz.net/monitoring.json?clientId=%@", email];
    NSURL *url = [NSURL URLWithString:stringUrl];
    
    // Submetendo a requisição sem o NSMutableURLRequest, assim vai com GET.
    NSError *error;
    NSData *resultado = [NSData dataWithContentsOfURL:url options:NSDataReadingUncached error:&error];
    
    // Verificando a ocorrência de erros HTTP.
    if(error){
        NSLog(@"Erro HTTP: %@", error.description);
        return nil;
    }
    
    // Acessando o elemento "data" da estrutura retornada pelo serviço.
    NSDictionary *respostaJSON = [NSJSONSerialization JSONObjectWithData:resultado options:kNilOptions error:nil];
    NSArray *monitoramentosJSON = [respostaJSON objectForKey:@"data"];
    
    Monitoramento *monitoramento;
    NSMutableArray *monitoramentos = [[NSMutableArray alloc] init];
    
    // Obtendo cada um dos monitoramentos retornados e preenchendo o array.
    for(NSDictionary *monitoramentoJSON in monitoramentosJSON) {
        monitoramento = [[Monitoramento alloc] init];

        monitoramento.objeto = [monitoramentoJSON objectForKey:@"trackId"];
        monitoramento.email = [monitoramentoJSON objectForKey:@"clientId"];

        [monitoramentos addObject:monitoramento];
    }
    
    return monitoramentos;
}

+ (BOOL)excluir:(Monitoramento *)monitoramento
{
    // URL do serviço de monitoramento no ambiente de testes (sandbox).
    NSString *stringUrl = [NSString stringWithFormat:@"http://services.sandbox.encomendaz.net/monitoring.json?clientId=%@&trackId=%@", monitoramento.email, monitoramento.objeto];
    NSURL *url = [NSURL URLWithString:stringUrl];
    
    // Criando a requisição com o método DELETE.
    NSMutableURLRequest *request = [NSMutableURLRequest requestWithURL:url];
    [request setHTTPMethod:@"DELETE"];
    
    // Submetendo a requisição e obtendo o resultado.
    NSError *error;
    NSData *resultado = [NSURLConnection sendSynchronousRequest:request returningResponse:nil error:&error];
    
    // Verificando a ocorrência de erros HTTP.
    if(error){
        NSLog(@"Erro HTTP: %@", error.description);
        return NO;
    }
    
    // O serviço retorna JSON com o resultado da operação, mas, por enquanto, vou ignorar para facilitar o entedimento.
    NSLog(@"Resultado: %@", [[NSString alloc] initWithData:resultado encoding:NSUTF8StringEncoding]);
    
    return YES;
}

@end
