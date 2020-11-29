
using Infrastructure.Models;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Infrastructure.Data.Common
{
    /// <typeparam name="T"></typeparam>
    public class EfRepository<T> : IAsyncRepository<T> where T : BaseEntity, IAggregateRoot
    {
        protected readonly MetadataPGContext _dbContext;

        /// <summary>
        /// Constructor que recibe como parámetro una instancia de CatalogContext y lo asigne a _dbContext
        /// </summary>
        /// <param name="dbContext"></param>
        /// 
        public EfRepository(MetadataPGContext dbContext)
        {
            _dbContext = dbContext;
        }

        public virtual async Task<T> GetByIdAsync(int id)
        {
            return await _dbContext.Set<T>().FindAsync(id);
        }

        public async Task<IReadOnlyList<T>> ListAllAsync()
        {
            return await _dbContext.Set<T>().ToListAsync();
        }

        public async Task<T> AddAsync(T entity)
        {
            await _dbContext.Set<T>().AddAsync(entity);
            await _dbContext.SaveChangesAsync();

            return entity;
        }

        public async Task UpdateAsync(T entity)
        {
            _dbContext.Entry(entity).State = EntityState.Modified;
            await _dbContext.SaveChangesAsync();
        }

        public async Task DeleteAsync(T entity)
        {
            _dbContext.Set<T>().Remove(entity);
            await _dbContext.SaveChangesAsync();
        }

    }
}
